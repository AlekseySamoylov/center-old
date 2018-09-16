package com.alekseysamoylov.repair.center.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
public class MyConfigurationTest {

    @Test
    public void rolesTest() {
        MyConfiguration configuration = new MyConfiguration();
            Assert.assertEquals(new Long(3), configuration.getClientRoleId());
            Assert.assertEquals(new Long(2), configuration.getMasterRoleId());


    }
}
