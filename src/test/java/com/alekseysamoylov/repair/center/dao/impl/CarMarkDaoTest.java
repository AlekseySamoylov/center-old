package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.CarMark;
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
public class CarMarkDaoTest {

    CarMark oldEntity;
    CarMark newEntity;
    @Autowired
    @Qualifier("carMarkDaoImpl")
    EntityDao<CarMark> entityDao;

    @Before
    public void before() {
        CreateBefore createBefore = new CreateBefore();
        createBefore.prepareMark();
        newEntity = createBefore.carMark;
        oldEntity = entityDao.get((long) 1);
    }

    @Test
    public void get() {
        Assert.assertEquals("Acura", entityDao.get((long) 1).getName());
    }

    @Test
    public void add() {
        Long id2 = entityDao.add(newEntity);
        Assert.assertTrue(id2 > 0);
    }

    @Test
    public void add2() {
        Long id = entityDao.add(newEntity);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void edit() {
        oldEntity.setName("Mercedes");
        Assert.assertTrue(entityDao.edit(oldEntity));
        Assert.assertEquals("Mercedes", entityDao.get(oldEntity.getId()).getName());
    }



    @Test
    public void findConcrete() {
        List<CarMark> list = entityDao.find("cu");
        Assert.assertEquals("Acura", list.get(0).getName());
    }

    @Test
    public void findAll() {
        Assert.assertNotNull(entityDao.getAll());
    }

//    @Test
//    public void delete() {
//        Assert.assertTrue(entityDao.delete((long)1));
//        Assert.assertNull(entityDao.get((long)1));
//    }


}
