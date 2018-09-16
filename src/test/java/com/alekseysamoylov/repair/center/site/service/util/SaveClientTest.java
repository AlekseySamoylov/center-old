package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
import com.alekseysamoylov.repair.center.site.model.element.WebClient;
import com.alekseysamoylov.repair.center.site.service.fill.FillWebList;
import com.alekseysamoylov.repair.center.site.service.util.ClientSaving;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class SaveClientTest {

    @Autowired
    private ClientSaving clientSaving;

    @Autowired
    @Qualifier("fillClientList")
    private FillWebList<WebClient> fillWebList;

    @Autowired
    @Qualifier("repairAccountDaoImpl")
    EntityDao<RepairAccount> accountDao;

    @Test
    public void saveNewClientTest() throws IOException {
        Long id = clientSaving.saveClientFromWebToDatabase("Dmitri", "Ivanov", "89999999992", 1L);
        fillWebList.getFilledWebList().getWebList().forEach(System.out::println);
        Assert.assertEquals("VETEROK", accountDao.get(id).getCompanies().get(0).getName());
    }
}
