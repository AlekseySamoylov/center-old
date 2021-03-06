package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.PurchaseLog;
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
 * Created by alekseysamoylov on 7/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class PurchaseLogDaoTest {

    private PurchaseLog oldEntity;
    private PurchaseLog newEntity;

    @Autowired
    @Qualifier("purchaseLogDaoImpl")
    private EntityDao<PurchaseLog> entityDao;

    @Before
    public void before() {
        CreateBefore createBefore = new CreateBefore();
        createBefore.preparePurchaseLog();
        newEntity = createBefore.purchaseLog;
        oldEntity = entityDao.get((long) 1);
    }

    @Test
    public void add() {
        Long id = entityDao.add(newEntity);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void edit() {
        oldEntity.setName("Удалили запись о продаже Колеса");
        Assert.assertTrue(entityDao.edit(oldEntity));
        Assert.assertEquals("Удалили запись о продаже Колеса", entityDao.get(oldEntity.getId()).getName());
    }

    @Test
    public void get() {
        Assert.assertEquals("22.07.2016 15:05 Продали фильтр с маслом", entityDao.get((long) 1).getName());
    }

    @Test
    public void findConcrete() {
        List<PurchaseLog> list = entityDao.find("22.07.2016");
        Assert.assertEquals("22.07.2016 15:05 Продали фильтр с маслом", list.get(0).getName());
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
