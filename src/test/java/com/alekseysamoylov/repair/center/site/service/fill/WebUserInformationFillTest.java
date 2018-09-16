package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.site.model.element.WebUserInformation;
import com.alekseysamoylov.repair.center.site.service.util.FillWebObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by alekseysamoylov on 7/15/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class WebUserInformationFillTest {

    @Autowired
    @Qualifier("webUserInformationFill")
    FillWebObject<WebUserInformation, String> webUserInformationFill;

    @Test
    public void test1() {
        WebUserInformation webUserInformation = webUserInformationFill.getFilledWebObject("OLEG");
        System.out.println(webUserInformation);
    }
}

