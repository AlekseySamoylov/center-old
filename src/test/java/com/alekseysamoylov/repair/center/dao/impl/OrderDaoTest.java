package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class OrderDaoTest {

    RepairOrder oldEntity;
    RepairOrder newEntity;

    @Autowired
    @Qualifier("orderDaoImpl")
    EntityDao<RepairOrder> entityDao;

    @Before
    public void before() {
        CreateBefore createBefore = new CreateBefore();
        createBefore.prepareRole();
        createBefore.prepareAccounts();
        createBefore.prepareModel();
        createBefore.prepareRepairOrder();
        newEntity = createBefore.repairOrder;
        oldEntity = entityDao.get((long)1);
    }

    @Test
    public void add() {
        Long id = entityDao.add(newEntity);
        Assert.assertTrue(id > 0);
    }
    @Test
    public void edit() {
        oldEntity.setOrderAdvice("Ok");
        entityDao.edit(oldEntity);
        Assert.assertEquals("Ok", entityDao.get((long)1).getOrderAdvice());
    }

    @Test
    public void get() {
        Assert.assertNotNull(entityDao.get((long)1));
    }

    @Test
    public void findConcrete() {
        List<RepairOrder> list = entityDao.find("колеса");
        Assert.assertEquals(
                "Стук в районе переднего правого колеса, Заменить масло.",
                list.get(0).getName());
    }

    @Test
    public void findAll() {
        Assert.assertNotNull(entityDao.getAll());
    }

    @Test
    public void delete() {
        Assert.assertTrue(entityDao.delete((long)1));
        Assert.assertNull(entityDao.get((long)1));
    }

}
