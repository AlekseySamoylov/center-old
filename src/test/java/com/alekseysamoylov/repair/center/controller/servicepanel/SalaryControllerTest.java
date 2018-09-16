package com.alekseysamoylov.repair.center.controller.servicepanel;

import com.alekseysamoylov.repair.center.AbstractTest;
import com.alekseysamoylov.repair.center.mockies.PrepareUser;
import com.alekseysamoylov.repair.center.site.model.json.SalaryEmployee;
import com.alekseysamoylov.repair.center.site.model.json.SalaryWork;
import com.alekseysamoylov.repair.center.site.service.util.SalaryStorage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alekseysamoylov on 8/6/16.
 */
@ContextConfiguration({"classpath:dispatcher-servlet.xml", "classpath:spring-security.xml"})
public class SalaryControllerTest extends AbstractTest {


    @Mock
    SalaryStorage salaryStorage;

    @InjectMocks
    SalaryController salaryController;

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private List<SalaryEmployee> employees = new ArrayList<>();


    @Before
    public void doBefore() {
        PrepareUser prepareUser = new PrepareUser();
        prepareUser.setRole("ROLE_MANAGER");


        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(salaryController).build();
        SalaryEmployee salaryEmployee = new SalaryEmployee();
        salaryEmployee.name = "Василий Иванов";
        SalaryWork salaryWork = new SalaryWork();
        salaryWork.name = "Rep.Engine";
        salaryWork.price = BigDecimal.valueOf(500);
        salaryEmployee.works.add(salaryWork);
        employees.add(salaryEmployee);
        salaryWork = new SalaryWork();
        salaryWork.name = "Rep.Suspension";
        salaryWork.price = BigDecimal.valueOf(1000);
        salaryEmployee.works.add(salaryWork);
        employees.add(salaryEmployee);

    }

    @Test
    public void goSalary() throws Exception {
        when(salaryStorage.getSalaryEmployee()).thenReturn(employees);
        mockMvc.perform(get("/service-panel/salary"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.[0].name").value("Василий Иванов"));
    }

}