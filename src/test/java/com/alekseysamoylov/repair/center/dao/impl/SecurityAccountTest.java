package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.AccountDao;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alekseysamoylov on 7/15/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class SecurityAccountTest {

    @Autowired
    AccountDao accountDao;

    @Test
    public void findByNameTest() {
        RepairAccount repairAccount = accountDao.findByName("OLEG");
        Assert.assertEquals("OLEG", repairAccount.getName());
    }
}
