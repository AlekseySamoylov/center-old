package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.PartStore;
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
public class PartStoreDaoTest {
    private PartStore oldEntity;
    private PartStore newEntity;

    @Autowired
    @Qualifier("partStoreDaoImpl")
    private EntityDao<PartStore> entityDao;

    @Before
    public void before() {
        CreateBefore createBefore = new CreateBefore();
        createBefore.preparePartStore();
        newEntity = createBefore.partStore;
        oldEntity = entityDao.get((long) 1);
    }

    @Test
    public void add() {
        Long id = entityDao.add(newEntity);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void edit() {
        oldEntity.setName("Болт на 12");
        Assert.assertTrue(entityDao.edit(oldEntity));
        Assert.assertEquals("Болт на 12", entityDao.get(oldEntity.getId()).getName());
    }

    @Test
    public void get() {
        Assert.assertEquals("Фильтр масляный BMW X6", entityDao.get((long) 1).getName());
    }

    @Test
    public void findConcrete() {
        List<PartStore> list = entityDao.find("BMW");
        Assert.assertEquals("Фильтр масляный BMW X6", list.get(0).getName());
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
