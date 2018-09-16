package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.site.model.WebElement;
import com.alekseysamoylov.repair.center.site.service.fill.FillWebList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class FillClientListTest {
    @Autowired
    @Qualifier("fillClientList")
    FillWebList<WebElement> fillWebList;

    @Test
    public void createOrderListTest() {
        Assert.assertEquals("Абросенков Алексей", fillWebList.getFilledWebList().getWebList().get(0).getName());
    }

}
