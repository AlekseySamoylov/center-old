package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.OrderPart;
import com.alekseysamoylov.repair.center.model.entity.OrderRating;
import org.junit.After;
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

import static org.junit.Assert.*;

/**
 * Created by alekseysamoylov on 7/29/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class OrderRatingDaoImplTest {

    OrderRating oldEntity;
    OrderRating newEntity;

    @Autowired
    @Qualifier("orderRatingDaoImpl")
    private EntityDao<OrderRating> entityDao;

    @Before
    public void before() {
        CreateBefore createBefore = new CreateBefore();
        createBefore.prepareRole();
        createBefore.prepareAccounts();
        createBefore.prepareModel();
        createBefore.prepareRepairOrder();
        createBefore.prepareOrderRating();
        newEntity = createBefore.orderRating;
        oldEntity = entityDao.get((long)1);
    }

    @Test
    public void add() {
        Long id = entityDao.add(newEntity);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void edit() {
        oldEntity.setName("Сайлентблок R332");
        Assert.assertTrue(entityDao.edit(oldEntity));
        Assert.assertEquals("Сайлентблок R332", entityDao.get(oldEntity.getId()).getName());
    }

    @Test
    public void get() {
        Assert.assertEquals("ALL WAS OK!", entityDao.get((long) 1).getName());
    }

    @Test
    public void findConcrete() {
        List<OrderRating> list = entityDao.find("WAS");
        Assert.assertEquals("ALL WAS OK!", list.get(0).getName());
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