package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairPrice;
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
public class RepairPriceDaoTest {
    private RepairPrice oldEntity;
    private RepairPrice newEntity;

    @Autowired
    @Qualifier("repairPriceDaoImpl")
    EntityDao<RepairPrice> entityDao;

    @Before
    public void before() {
        CreateBefore createBefore = new CreateBefore();
        createBefore.preparePrice();
        newEntity = createBefore.repairPrice;
        oldEntity = entityDao.get((long) 1);
    }

    @Test
    public void add() {
        Long id = entityDao.add(newEntity);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void edit() {
        oldEntity.setName("Подкачка");
        Assert.assertTrue(entityDao.edit(oldEntity));
        Assert.assertEquals("Подкачка", entityDao.get(oldEntity.getId()).getName());
    }

    @Test
    public void get() {
        Assert.assertEquals("Датчик скорости колеса замена", entityDao.get((long) 1).getName());
    }

    @Test
    public void findConcrete() {
        List<RepairPrice> list = entityDao.find("скорости колеса");
        Assert.assertEquals("Датчик скорости колеса замена", list.get(0).getName());
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
