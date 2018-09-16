package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairOrder;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderTemplate;
import com.alekseysamoylov.repair.center.site.service.util.WebOrderStorage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alekseysamoylov on 7/8/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class SaveNewOrderTest {

    @Autowired
    @Qualifier("orderDaoImpl")
    EntityDao<RepairOrder> repairOrderDao;

    @Autowired
    WebOrderStorage webOrderStorage;

    private WebOrderTemplate webOrderTemplate;



    @Test
    public void createOrderListTest1() {
        webOrderTemplate = new WebOrderTemplate();
        webOrderTemplate.setCompanyId(1L);
        webOrderTemplate.setClientId(3L);
        webOrderTemplate.setManagerId(1L);
        webOrderTemplate.setClientInBase(true);
        webOrderTemplate.setClientCarInList(true);
        webOrderTemplate.setClientCarId(1L);
        webOrderTemplate.setMainOrderText("Подкачайте колеса");
        Long id = webOrderStorage.saveNewWebOrder(webOrderTemplate);
        Assert.assertEquals("Подкачайте колеса", repairOrderDao.get(id).getName());
    }


    @Test
    public void createOrderListTest2() {
        webOrderTemplate = new WebOrderTemplate();
        webOrderTemplate.setCompanyId(1L);
        webOrderTemplate.setManagerId(1L);
        webOrderTemplate.setClientInBase(false);
        webOrderTemplate.setFirstName("Вася");
        webOrderTemplate.setLastName("Васильев");
        webOrderTemplate.setPhone("34324345");
        webOrderTemplate.setClientCarInList(false);
        webOrderTemplate.setCarModelId(4L);
        webOrderTemplate.setClientCarNumber("A342EE");
        webOrderTemplate.setMainOrderText("Замена воздушного фильтра");
        Long id = webOrderStorage.saveNewWebOrder(webOrderTemplate);
        Assert.assertEquals("Замена воздушного фильтра", repairOrderDao.get(id).getName());
    }

    @Test
    public void createOrderListTest3() {
        webOrderTemplate = new WebOrderTemplate();
        webOrderTemplate.setCompanyId(1L);
        webOrderTemplate.setManagerId(1L);
        webOrderTemplate.setClientInBase(true);
        webOrderTemplate.setClientId(1L);

        webOrderTemplate.setClientCarInList(false);
        webOrderTemplate.setCarModelId(4L);
        webOrderTemplate.setClientCarNumber("A342EE");
        webOrderTemplate.setMainOrderText("Замена воздушного фильтра");
        Long id = webOrderStorage.saveNewWebOrder(webOrderTemplate);
        System.out.println("sadfdas");
        Assert.assertEquals("Замена воздушного фильтра", repairOrderDao.get(id).getName());
    }
}
