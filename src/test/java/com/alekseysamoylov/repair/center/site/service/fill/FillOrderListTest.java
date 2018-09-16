package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.site.model.WebElement;
import com.alekseysamoylov.repair.center.site.model.element.WebOrder;
import com.alekseysamoylov.repair.center.site.service.fill.FillWebList;
import com.alekseysamoylov.repair.center.site.service.util.WebOrderStorage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.Collectors;

/**
 * Created by alekseysamoylov on 7/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class FillOrderListTest {

    @Autowired
    @Qualifier("fillOrderList")
    FillWebList<WebOrder> fillWebList;

    @Autowired
    WebOrderStorage webOrderStorage;

    @Test
    public void createOrderListTest() {
        Assert.assertEquals("Acura CL A123AA 159", fillWebList.getFilledWebList().getWebList().get(0).getName());
    }
    @Test
    public void getFilledWebList() {
        Assert.assertEquals("Acura CL A123AA 159", fillWebList.getFilledWebList(1L).getWebList().get(0).getName());

    }

    @Test
    public void webOrderEqualsTest() {
        WebOrder webOrder1 = new WebOrder();
        webOrder1.setId(1L);
        WebOrder webOrder2 = new WebOrder();
        webOrder2.setId(1L);
        Assert.assertTrue(webOrder1.equals(webOrder2));

    }
}
