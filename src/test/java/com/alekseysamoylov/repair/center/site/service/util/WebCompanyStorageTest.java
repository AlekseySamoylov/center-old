package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.site.model.element.WebCompany;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by alekseysamoylov on 7/21/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class WebCompanyStorageTest {

    @Autowired
    WebCompanyStorage webCompanyStorage;

    @Autowired
    FillWebObject<WebCompany, Long> webCompanyInformationFill;

    private WebCompany webCompany;

    @Before
    public void createWebCompany() {
        webCompany = webCompanyInformationFill.getFilledWebObject(1L);
        webCompany.setName("Veter");
    }

    @Test
    public void saveWebCompany() throws Exception {
        webCompanyStorage.saveWebCompany(webCompany);
        Assert.assertEquals("Veter", webCompanyInformationFill.getFilledWebObject(1L).getName());


    }
}