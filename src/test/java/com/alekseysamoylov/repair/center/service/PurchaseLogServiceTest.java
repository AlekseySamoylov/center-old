package com.alekseysamoylov.repair.center.service;

import com.alekseysamoylov.repair.center.AbstractTest;
import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.PurchaseLog;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by alekseysamoylov on 7/25/16.
 */
public class PurchaseLogServiceTest extends AbstractTest {

    @Autowired
    @Qualifier("purchaseLogDaoImpl")
    EntityDao<PurchaseLog> purchaseLogEntityDao;

    @Autowired
    PurchaseLogService purchaseLogService;

    @Test
    public void log() throws Exception {
        long id = purchaseLogService.log("Buy Parts");
        Assert.assertNotNull(purchaseLogEntityDao.get(id));
    }

}