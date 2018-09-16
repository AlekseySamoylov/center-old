package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.site.model.element.WebOrder;
import com.alekseysamoylov.repair.center.site.service.util.WebOrderStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/15/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class OrderStorageTest {

    @Autowired
    WebOrderStorage webOrderStorage;

    @Test
    public void getOrderById() {
        List<WebOrder> webOrders = webOrderStorage.getOrdersByClientId(3L);
        System.out.println(webOrders.get(0));
    }
}
