package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.AbstractTest;
import com.alekseysamoylov.repair.center.dao.AccountByCompany;
import com.alekseysamoylov.repair.center.mockies.EntityMocks;
import com.alekseysamoylov.repair.center.mockies.PrepareUser;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
import com.alekseysamoylov.repair.center.site.model.json.SalaryEmployee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Mockito.when;

/**
 * Created by alekseysamoylov on 8/7/16.
 */
public class SalaryStorageImplTest extends AbstractTest {
    @Mock
    AccountByCompany accountByCompany;

    @InjectMocks
    SalaryStorageImpl salaryStorage;

    private MockMvc mockMvc;
    private EntityMocks entityMocks;
    private PrepareUser prepareUser;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        entityMocks = new EntityMocks();
        entityMocks.prepareOrderWork();
        prepareUser = new PrepareUser();
        prepareUser.setRole("ROLE_MANAGER");
    }

    @Test
    public void getSalaryEmployee() throws Exception {
        List<RepairAccount> masters = new ArrayList<>();
        masters.add(entityMocks.master);
        when(accountByCompany.getAllMasters()).thenReturn(masters);
        SalaryEmployee salaryEmployee = salaryStorage.getSalaryEmployee().get(0);
        System.out.println(salaryEmployee);
        Assert.assertEquals("Замена руля", salaryStorage.getSalaryEmployee().get(0).works.get(0).name);
//        Mockito.verify(accountByCompany).getAllManagers();

    }

}