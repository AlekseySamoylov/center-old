package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.Company;
import com.alekseysamoylov.repair.center.site.model.element.WebCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alekseysamoylov on 7/15/16.
 */
@Service
@Transactional
public class WebCompanyInformationFill implements FillWebObject<WebCompany, Long> {

    private final EntityDao<Company> companyDao;

    @Autowired
    public WebCompanyInformationFill(@Qualifier("companyDaoImpl") EntityDao<Company> companyDao) {
        this.companyDao = companyDao;
    }

    @Transactional(readOnly = true)
    public WebCompany getFilledWebObject(Long id) {
        WebCompany webCompany = new WebCompany();
        Company dbCompany = companyDao.get(id);
        webCompany.setId(id);
        webCompany.setName(dbCompany.getName());
        webCompany.setCountry(dbCompany.getCountry());
        webCompany.setCity(dbCompany.getCity());
        webCompany.setDistrict(dbCompany.getDistrict());
        webCompany.setStreet(dbCompany.getStreet());
        webCompany.setHouse(dbCompany.getHouse());
        webCompany.setPhone(dbCompany.getPhone());
        webCompany.setEmail(dbCompany.getEmail());
        webCompany.setSite(dbCompany.getSite());
        webCompany.setOther(dbCompany.getOther());
        return webCompany;
    }
}
