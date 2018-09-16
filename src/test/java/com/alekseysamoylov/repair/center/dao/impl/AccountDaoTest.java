package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
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
public class AccountDaoTest {
    RepairAccount oldEntity;
    RepairAccount newEntity;

    @Autowired
    @Qualifier("repairAccountDaoImpl")
    EntityDao<RepairAccount> entityDao;

    @Before
    public void before() {
        CreateBefore createBefore = new CreateBefore();
        createBefore.prepareAccounts();
        newEntity = createBefore.client;
        oldEntity = entityDao.get((long) 3);
    }

    @Test
    public void add() {
        Long id = entityDao.add(newEntity);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void edit() {
        oldEntity.setName("Vova");
        Assert.assertTrue(entityDao.edit(oldEntity));
        Assert.assertEquals("Vova", entityDao.get(oldEntity.getId()).getName());
    }

    @Test
    public void get() {
        Assert.assertEquals("ZAKOPTELOV", entityDao.get((long) 3).getName());
    }

    @Test
    public void findConcrete() {
        List<RepairAccount> list = entityDao.find("VA");
        Assert.assertEquals("VASHKARIN", list.get(0).getName());
    }

    @Test
    public void findAll() {
        Assert.assertNotNull(entityDao.getAll());
    }

    @Test
    public void delete() {
        Assert.assertTrue(entityDao.delete((long)17));
        Assert.assertNull(entityDao.get((long)17));
    }

}
