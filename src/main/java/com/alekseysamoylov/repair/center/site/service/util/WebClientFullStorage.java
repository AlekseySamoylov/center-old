package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.AccountProperty;
import com.alekseysamoylov.repair.center.model.entity.Company;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
import com.alekseysamoylov.repair.center.model.entity.RepairAccountRole;
import com.alekseysamoylov.repair.center.service.MyConfiguration;
import com.alekseysamoylov.repair.center.service.security.MyPasswordEncoder;
import com.alekseysamoylov.repair.center.site.model.element.WebClientFull;
import com.alekseysamoylov.repair.center.site.model.element.WebUserInformation;
import com.alekseysamoylov.repair.center.site.model.form.NormalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/30/16.
 */
@Service
@Transactional
public class WebClientFullStorage {

    private final EntityDao<RepairAccount> accountDao;
    private final EntityDao<RepairAccountRole> entityRoleDao;
    private final EntityDao<Company> companyDao;
    private final MyPasswordEncoder passwordEncoder;
    private final MyConfiguration configuration;

    @Autowired
    public WebClientFullStorage (@Qualifier("repairAccountDaoImpl") EntityDao<RepairAccount> accountDao,
                                 @Qualifier("repairAccountRoleDaoImpl") EntityDao<RepairAccountRole> entityRoleDao,
                                 @Qualifier("companyDaoImpl") EntityDao<Company> companyDao,
                                 MyPasswordEncoder passwordEncoder,
                                 MyConfiguration configuration) {
        this.accountDao = accountDao;
        this.entityRoleDao = entityRoleDao;
        this.companyDao = companyDao;
        this.passwordEncoder = passwordEncoder;
        this.configuration = configuration;
    }

    @Transactional(readOnly = true)
    public List<WebClientFull> getAllUsers() {
        Long companyId = ((WebUserInformation)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCompanyId();
        Company company = new Company();
        company.setId(companyId);
        List<WebClientFull> webClientFullList = new ArrayList<>();
        accountDao.getAll().stream().filter(account->account.getCompanies().contains(company)).forEach(account -> {
            WebClientFull webClientFull = new WebClientFull();
            //id
            webClientFull.setId(account.getId());
            //full name
            webClientFull.setName(account.getAccountProperty().getName()
                + " " + account.getAccountProperty().getLastName());
            //cars full
            webClientFull.setCar(account.getAccountCars().toString());
            //
            webClientFull.setPhone(account.getAccountProperty().getPhoneNumber());
            webClientFullList.add(webClientFull);
        });
        return webClientFullList;
    }

    @Transactional(readOnly = true)
    public List<String> getAllLogins() {
        List<String> logins = new ArrayList<>();
        accountDao.getAll().forEach(account -> {
            logins.add(account.getName());
        });
        return logins;
    }

    @Transactional
    public void deleteUser (Long id) {
        accountDao.delete(id);
    }


    public List<WebClientFull> getAllEmployees() {
        Long companyId = ((WebUserInformation)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCompanyId();
        Company company = new Company();
        company.setId(companyId);
        List<WebClientFull> webClientFullList = new ArrayList<>();
        accountDao.getAll().stream().filter(account->account.getCompanies().contains(company)).filter(account -> (account.getRepairAccountRole().getId() < 3)).forEach(account -> {
            WebClientFull webClientFull = new WebClientFull();
            //id
            webClientFull.setId(account.getId());
            //full name
            webClientFull.setName(account.getAccountProperty().getName()
                    + " " + account.getAccountProperty().getLastName());
            //cars full
            webClientFull.setCar(account.getAccountCars().toString());
            //phone
            webClientFull.setPhone(account.getAccountProperty().getPhoneNumber());
            //role
            webClientFull.setRole(account.getRepairAccountRole().getName());
            webClientFullList.add(webClientFull);
        });
        return webClientFullList;
    }

    public void setClient(Long id) {
        RepairAccountRole repairAccountRole = entityRoleDao.get(3L);
        RepairAccount repairAccount = accountDao.get(id);
        repairAccount.setRepairAccountRole(repairAccountRole);
        accountDao.edit(repairAccount);
    }
    public void setMaster(Long id) {
        RepairAccountRole repairAccountRole = entityRoleDao.get(2L);
        RepairAccount repairAccount = accountDao.get(id);
        repairAccount.setRepairAccountRole(repairAccountRole);
        accountDao.edit(repairAccount);
    }
    public void setManager(Long id) {
        RepairAccountRole repairAccountRole = entityRoleDao.get(1L);
        RepairAccount repairAccount = accountDao.get(id);
        repairAccount.setRepairAccountRole(repairAccountRole);
        accountDao.edit(repairAccount);
    }

    public void saveNormalUser(NormalUser normalUser) {
        RepairAccount repairAccount = new RepairAccount();
        repairAccount.setName(normalUser.getPhone());
        repairAccount.setRepairAccountPassword(passwordEncoder.encodePassword(normalUser.getPhone()));
        repairAccount.setRepairAccountRole(entityRoleDao.get(configuration.getClientRoleId()));
        AccountProperty accountProperty = new AccountProperty();
        accountProperty.setName(normalUser.getFirstName());
        accountProperty.setLastName(normalUser.getLastName());
        accountProperty.setPhoneNumber(normalUser.getPhone());
        accountProperty.setAccount(repairAccount);

        repairAccount.setAccountProperty(accountProperty);

        Company company = companyDao
                .get(((WebUserInformation)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal())
                .getCompanyId());

        repairAccount.getCompanies().add(company);
        Long clientId = accountDao.add(repairAccount);
        company.getMembers().add(repairAccount);
        companyDao.edit(company);
    }
}
