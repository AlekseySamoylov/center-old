package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.AbstractTest;
import com.alekseysamoylov.repair.center.mockies.PrepareUser;
import com.alekseysamoylov.repair.center.dao.AccountByCompany;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

/**
 * Created by alekseysamoylov on 8/7/16.
 */
public class AccountByCompanyImplTest extends AbstractTest {

    @Autowired
    AccountByCompany accountByCompany;

    @Before
    public void setUp() {
        PrepareUser prepareUser = new PrepareUser();
        prepareUser.setRole("ROLE_MANAGER");
    }

    @Test
    public void getAllManagers() throws Exception {
        Assert.assertNotNull(accountByCompany.getAllManagers());
        Assert.assertEquals("ROLE_MANAGER", accountByCompany.getAllManagers().get(0).getRepairAccountRole().getName());
    }

    @Test
    public void getAllMasters() throws Exception {
        Assert.assertNotNull(accountByCompany.getAllMasters());
        Assert.assertEquals("ROLE_MASTER", accountByCompany.getAllMasters().get(0).getRepairAccountRole().getName());
    }

    @Test
    public void getAllClients() throws Exception {
        Assert.assertNotNull(accountByCompany.getAllClients());
        Assert.assertEquals("ROLE_CLIENT", accountByCompany.getAllClients().get(0).getRepairAccountRole().getName());
    }

}