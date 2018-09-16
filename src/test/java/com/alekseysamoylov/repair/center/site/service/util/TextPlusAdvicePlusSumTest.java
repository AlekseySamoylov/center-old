package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairOrder;
import com.alekseysamoylov.repair.center.site.model.element.WebTextPlusAdvice;
import com.alekseysamoylov.repair.center.site.service.util.WebPurchaseOrderStorage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by alekseysamoylov on 7/13/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class TextPlusAdvicePlusSumTest {

    @Autowired
    WebPurchaseOrderStorage webPurchaseOrderStorage;

    @Autowired
    @Qualifier("orderDaoImpl")
    EntityDao<RepairOrder> orderDao;

    @Test
    public void plusTest() {
        WebTextPlusAdvice webTextPlusAdvice = new WebTextPlusAdvice();
        webTextPlusAdvice.setAdvice("Hi!");
        webTextPlusAdvice.setId(1L);
        webTextPlusAdvice.setFinalSum(BigDecimal.valueOf(123));
        webTextPlusAdvice.setNoFinalSum(BigDecimal.valueOf(100));
        webTextPlusAdvice.setDiscount(10);
        webTextPlusAdvice.setPrepayment(BigDecimal.valueOf(50));
        webPurchaseOrderStorage.saveTextPlusAdvice(webTextPlusAdvice);
        Assert.assertNotNull(orderDao.get(1L).getOrderSum());
    }
}
