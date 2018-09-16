package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.site.model.WebElement;
import com.alekseysamoylov.repair.center.site.model.element.WebCarMark;
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
public class FillCarMarkListTest {
    @Autowired
    @Qualifier("fillCarMarkList")
    FillWebList<WebElement> fillWebList;

    @Test
    public void createOrderListTest() {
        Assert.assertEquals("Acura", fillWebList.getFilledWebList().getWebList().get(0).getName());
        Assert.assertEquals("CL", ((WebCarMark)fillWebList.getFilledWebList().getWebList().get(0)).getModels().get(0).getName());
    }
}