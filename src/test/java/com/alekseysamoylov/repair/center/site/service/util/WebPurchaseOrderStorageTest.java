package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairOrder;
import com.alekseysamoylov.repair.center.site.model.element.WebPurchaseOrderTemplate;
import com.alekseysamoylov.repair.center.site.model.element.WebTextPlusAdvice;
import com.alekseysamoylov.repair.center.site.service.util.WebPurchaseOrderStorage;
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
public class WebPurchaseOrderStorageTest {
    @Autowired
    private WebPurchaseOrderStorage purchaseOrderStorage;

    @Autowired
    @Qualifier("orderDaoImpl")
    private EntityDao<RepairOrder> orderDao;

    @Test
    public void loadPurchaseOrderTemplateTest() {
        WebPurchaseOrderTemplate webPurchaseOrderTemplate = purchaseOrderStorage.loadTemplate(1L);
        Assert.assertEquals("Ok", webPurchaseOrderTemplate.getName());
        Assert.assertEquals("Замена шаровой", webPurchaseOrderTemplate.getWebOrderWorkTable().getWebList().get(0).getName());
        Assert.assertEquals("Рулевая рейка", webPurchaseOrderTemplate.getWebOrderPartTable().getWebList().get(0).getName());

    }

    @Test
    public void saveTextPlusAdviceTest() {
        WebTextPlusAdvice textPlusAdvice = new WebTextPlusAdvice();
        textPlusAdvice.setId(1L);
        textPlusAdvice.setText("Ok");
        textPlusAdvice.setAdvice("Good");
        purchaseOrderStorage.saveTextPlusAdvice(textPlusAdvice);
        RepairOrder repairOrder = orderDao.get(1L);
        Assert.assertEquals("Good", repairOrder.getOrderAdvice());
        Assert.assertEquals("OLEG", repairOrder.getManager().getName());

    }
}
