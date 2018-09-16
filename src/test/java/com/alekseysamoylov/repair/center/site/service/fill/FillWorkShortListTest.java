package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.site.model.element.WebWorkShort;
import com.alekseysamoylov.repair.center.site.service.fill.FillWebList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class FillWorkShortListTest {
    @Autowired
    @Qualifier("fillWorkShortList")
    FillWebList<WebWorkShort> fillWebList;

    @Test
    public void createOrderListTest() {
        Assert.assertEquals("Датчик скорости колеса замена", fillWebList.getFilledWebList(1L).getWebList().get(0).getName());
    }

}
