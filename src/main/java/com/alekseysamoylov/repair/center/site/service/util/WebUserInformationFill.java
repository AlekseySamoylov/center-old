package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.AccountDao;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
import com.alekseysamoylov.repair.center.site.model.element.WebClientCar;
import com.alekseysamoylov.repair.center.site.model.element.WebCompany;
import com.alekseysamoylov.repair.center.site.model.element.WebUserInformation;
import com.alekseysamoylov.repair.center.site.service.fill.FillWebList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/15/16.
 */
@Service
@Transactional
public class WebUserInformationFill implements FillWebObject<WebUserInformation, String> {

    private final AccountDao accountDao;

    private final FillWebList<WebClientCar> carListFill;

    private final WebOrderStorage webOrderStorage;

    private final FillWebObject<WebCompany, Long> webCompanyInformationFill;

    @Autowired
    public WebUserInformationFill(@Qualifier("webCompanyInformationFill") FillWebObject<WebCompany, Long> webCompanyInformationFill, AccountDao accountDao, @Qualifier("fillClientCarList") FillWebList<WebClientCar> carListFill, WebOrderStorage webOrderStorage) {
        this.webCompanyInformationFill = webCompanyInformationFill;
        this.accountDao = accountDao;
        this.carListFill = carListFill;
        this.webOrderStorage = webOrderStorage;
    }

    @Transactional(readOnly = true)
    public WebUserInformation getFilledWebObject(String username) {
        RepairAccount repairAccount = accountDao.findByName(username);
        if (repairAccount == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        WebUserInformation webUser = new WebUserInformation();
        //id
        webUser.setId(repairAccount.getId());
        //company id !!!!!!!!
        webUser.setCompanyId(repairAccount.getCompanies().get(0).getId());
        //role
        webUser.setRole(repairAccount.getRepairAccountRole().getName());
        //userName
        webUser.setName(repairAccount.getName());
        //pass
        webUser.setPassword(repairAccount.getRepairAccountPassword());
        //firstName
        webUser.setFirstName(repairAccount.getAccountProperty().getName());
        //lastName
        webUser.setLastName(repairAccount.getAccountProperty().getLastName());
        //phone
        webUser.setPhone(repairAccount.getAccountProperty().getPhoneNumber());
        //eMail
        webUser.setOtherPhone(repairAccount.getAccountProperty().getPhoneNumberOther());
        //Companies
        List<WebCompany> companies = new ArrayList<>();
        repairAccount.getCompanies().forEach(dbCompany->{
            WebCompany webCompany = webCompanyInformationFill
                    .getFilledWebObject(dbCompany.getId());
            companies.add(webCompany);
        });
        webUser.setCompanies(companies);
        //cars
        webUser.setCars(carListFill
                .getFilledWebList(webUser.getId())
                .getWebList());
        //orders
        webUser.setOrders(webOrderStorage.getOrdersByClientId(webUser.getId()));
        return webUser;
    }

}
