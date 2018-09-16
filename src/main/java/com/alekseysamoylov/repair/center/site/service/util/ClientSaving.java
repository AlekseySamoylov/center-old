package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.AccountProperty;
import com.alekseysamoylov.repair.center.model.entity.Company;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
import com.alekseysamoylov.repair.center.model.entity.RepairAccountRole;
import com.alekseysamoylov.repair.center.service.MyConfiguration;
import com.alekseysamoylov.repair.center.service.security.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
@Service
@Transactional
public class ClientSaving {

    private final EntityDao<RepairAccount> accountDao;

    private final EntityDao<RepairAccountRole> roleDao;

    private final EntityDao<Company> companyDao;

    private final MyConfiguration configuration;

    private final MyPasswordEncoder passwordEncoder;

    @Autowired
    public ClientSaving(@Qualifier("companyDaoImpl") EntityDao<Company> companyDao, @Qualifier("repairAccountDaoImpl") EntityDao<RepairAccount> accountDao, MyConfiguration configuration, @Qualifier("repairAccountRoleDaoImpl") EntityDao<RepairAccountRole> roleDao, MyPasswordEncoder passwordEncoder) {
        this.companyDao = companyDao;
        this.accountDao = accountDao;
        this.configuration = configuration;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Save
     * Role
     * Account
     * AccountProperties
     * @param firstName
     * @param lastName
     * @param phone
     * @return
     * @throws IOException
     */
    @Transactional
    public Long saveClientFromWebToDatabase(String firstName, String lastName, String phone, Long companyId) throws IOException {
        if (firstName != null && lastName != null && phone != null) {
            RepairAccount repairAccount = new RepairAccount();
            repairAccount.setName(phone);
            repairAccount.setRepairAccountPassword(passwordEncoder.encodePassword("phone"));
            repairAccount.setRepairAccountRole(roleDao.get(configuration.getClientRoleId()));
            AccountProperty accountProperty = new AccountProperty();
            accountProperty.setName(firstName);
            accountProperty.setLastName(lastName);
            accountProperty.setPhoneNumber(phone);
            accountProperty.setAccount(repairAccount);

            repairAccount.setAccountProperty(accountProperty);

            Company company = companyDao.get(companyId);

            repairAccount.getCompanies().add(company);
            Long clientId = accountDao.add(repairAccount);
            company.getMembers().add(repairAccount);
            companyDao.edit(company);
            return clientId;
        } else {
            throw new NullPointerException(" No data from create client form");
        }
    }
}
