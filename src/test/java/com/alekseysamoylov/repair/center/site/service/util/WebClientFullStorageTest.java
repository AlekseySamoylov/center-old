package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.AbstractTest;
import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.mockies.PrepareUser;
import com.alekseysamoylov.repair.center.model.entity.AccountProperty;
import com.alekseysamoylov.repair.center.model.entity.Company;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
import com.alekseysamoylov.repair.center.site.model.element.WebUserInformation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by alekseysamoylov on 7/30/16.
 */
public class WebClientFullStorageTest extends AbstractTest {

    @Mock
    private EntityDao<RepairAccount> accountDao;

    @InjectMocks
    WebClientFullStorage fullStorage;

    @Before
    public void setUp() {
        PrepareUser prepareUser = new PrepareUser();
        prepareUser.setRole("ROLE_MANAGER");

        MockitoAnnotations.initMocks(this);
        RepairAccount repairAccount = new RepairAccount();
        repairAccount.setName("aleksey");
        Company company = new Company();
        company.setId(1L);
        repairAccount.getCompanies().add(company);
        repairAccount.setAccountProperty(new AccountProperty());
        List<RepairAccount> list = new ArrayList<>();
        list.add(repairAccount);
        when(accountDao.getAll()).thenReturn(list);
    }

    @Test
    public void getAllUsers() throws Exception {
        Assert.assertNotNull(fullStorage.getAllUsers().get(0));
        verify(accountDao).getAll();

    }

    @Test
    public void getLoginList() {
        Assert.assertNotNull(fullStorage.getAllLogins());
        verify(accountDao).getAll();

    }


}