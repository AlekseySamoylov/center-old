package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.AccountByCompany;
import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.Company;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
import com.alekseysamoylov.repair.center.model.entity.RepairAccountRole;
import com.alekseysamoylov.repair.center.service.MyConfiguration;
import com.alekseysamoylov.repair.center.site.model.element.WebUserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alekseysamoylov on 8/6/16.
 */
@Service
@Transactional
public class AccountByCompanyImpl implements AccountByCompany{


    private final HibernateTemplate template;
    private final MyConfiguration configuration;
    private final EntityDao<RepairAccountRole> roleEntityDao;

    @Autowired
    public AccountByCompanyImpl(HibernateTemplate template,
                                MyConfiguration configuration,
                                @Qualifier("repairAccountRoleDaoImpl")
                                EntityDao<RepairAccountRole> roleEntityDao) {
        this.template = template;
        this.configuration = configuration;
        this.roleEntityDao = roleEntityDao;
    }


    @Override
    @Transactional(readOnly = true)
    public List<RepairAccount> getAllManagers() {
        Company company = new Company();
        company.setId(
                ((WebUserInformation)SecurityContextHolder
                        .getContext().getAuthentication()
                        .getPrincipal())
                        .getCompanyId());
        return ((List<RepairAccount>) template
                .findByNamedParam("from RepairAccount r " +
                                "where r.repairAccountRole.name = :role",
                        "role",
                        roleEntityDao.get(configuration
                                .getManagerRoleId())
                                .getName())).stream().filter(account ->
                account.getCompanies()
                        .contains(company))
                .collect(Collectors.toList());    }

    @Override
    @Transactional(readOnly = true)
    public List<RepairAccount> getAllMasters() {
        Company company = new Company();
        company.setId(
                ((WebUserInformation)SecurityContextHolder
                        .getContext().getAuthentication()
                        .getPrincipal())
                                .getCompanyId());
        return ((List<RepairAccount>) template
                .findByNamedParam("from RepairAccount r " +
                        "where r.repairAccountRole.name = :role",
                        "role",
                        roleEntityDao.get(configuration
                                .getMasterRoleId())
                                    .getName())).stream().filter(account ->
                                        account.getCompanies()
                                                .contains(company))
                                                    .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RepairAccount> getAllClients() {
        Company company = new Company();
        company.setId(
                ((WebUserInformation)SecurityContextHolder
                        .getContext().getAuthentication()
                        .getPrincipal())
                        .getCompanyId());
        return ((List<RepairAccount>) template
                .findByNamedParam("from RepairAccount r " +
                                "where r.repairAccountRole.name = :role",
                        "role",
                        roleEntityDao.get(configuration
                                .getClientRoleId())
                                .getName())).stream().filter(account ->
                account.getCompanies()
                        .contains(company))
                .collect(Collectors.toList());
    }


}
