package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.AccountProperty;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
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

import java.util.List;

/**
 * Created by alekseysamoylov on 7/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class AccountPropertyDaoTest {

    AccountProperty oldEntity;
    AccountProperty newEntity;

    @Autowired
    @Qualifier("accountPropertyDaoImpl")
    EntityDao<AccountProperty> entityDao;
    @Autowired
    @Qualifier("repairAccountDaoImpl")
    EntityDao<RepairAccount> accountDao;
    @Autowired
    @Qualifier("repairAccountRoleDaoImpl")
    EntityDao<RepairAccountRole> roleDao;

    @Before
    public void before() {
        CreateBefore createBefore = new CreateBefore();
        createBefore.prepareAccounts();
        createBefore.prepareAccountProperties();
        newEntity = createBefore.clientProperty;
        createBefore.client.setRepairAccountRole(roleDao.get((long)2));
        Long id = accountDao.add(createBefore.client);
        oldEntity = entityDao.get((long) 1);
    }

    @Test
    public void add() {
        Long id = entityDao.add(newEntity);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void edit() {
        oldEntity.setLastName("Ivanov");
        Assert.assertTrue(entityDao.edit(oldEntity));
        Assert.assertEquals("Ivanov", entityDao.get(oldEntity.getId()).getLastName());
    }

    @Test
    public void get() {
        Assert.assertEquals("Новиков", entityDao.get((long) 1).getLastName());
    }

    @Test
    public void findConcrete() {
        List<AccountProperty> list = entityDao.find("");
        Assert.assertEquals("Новиков", list.get(0).getLastName());
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
