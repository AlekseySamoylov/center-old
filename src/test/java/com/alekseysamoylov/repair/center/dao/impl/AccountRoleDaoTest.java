package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairAccountRole;
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
 * Created by alekseysamoylov on 7/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class AccountRoleDaoTest {

    RepairAccountRole oldEntity;
    RepairAccountRole newEntity;

    @Autowired
    @Qualifier("repairAccountRoleDaoImpl")
    EntityDao<RepairAccountRole> entityDao;

    @Before
    public void before() {
        CreateBefore createBefore = new CreateBefore();
        createBefore.prepareRole();
        newEntity = createBefore.guestRole;
        oldEntity = entityDao.get((long) 1);
    }

    @Test
    public void add() {
        Long id = entityDao.add(newEntity);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void edit() {
        oldEntity.setName("ROLE_USER");
        Assert.assertTrue(entityDao.edit(oldEntity));
        Assert.assertEquals("ROLE_USER", entityDao.get(oldEntity.getId()).getName());
    }

    @Test
    public void get() {
        Assert.assertEquals("ROLE_MANAGER", entityDao.get((long) 1).getName());
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
