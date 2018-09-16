package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.Company;
import com.alekseysamoylov.repair.center.site.model.element.WebCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alekseysamoylov on 7/21/16.
 */
@Service
@Transactional
public class WebCompanyStorage {

    private final EntityDao<Company> companyDao;

    @Autowired
    public WebCompanyStorage(
            @Qualifier("companyDaoImpl")
            EntityDao<Company> companyDao) {
        this.companyDao = companyDao;
    }


    public void saveWebCompany(WebCompany webCompany) {
        Company dbCompany = companyDao.get(webCompany.getId());
        dbCompany.setName(webCompany.getName());
        dbCompany.setCountry(webCompany.getCountry());
        dbCompany.setCity(webCompany.getCity());
        dbCompany.setDistrict(webCompany.getDistrict());
        dbCompany.setStreet(webCompany.getStreet());
        dbCompany.setHouse(webCompany.getHouse());
        dbCompany.setPhone(webCompany.getPhone());
        dbCompany.setSite(webCompany.getSite());
        dbCompany.setEmail(webCompany.getEmail());
        dbCompany.setOther(webCompany.getOther());
        companyDao.edit(dbCompany);
    }
}
