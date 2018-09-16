package com.alekseysamoylov.repair.center.controller.main;

import com.alekseysamoylov.repair.center.AbstractTest;
import com.alekseysamoylov.repair.center.mockies.PrepareUser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by alekseysamoylov on 7/25/16.
 */
@ContextConfiguration({"classpath:dispatcher-servlet.xml", "classpath:spring-security.xml"})
public class WelcomeControllerTest extends AbstractTest {
    private PrepareUser prepareUser;
    @Autowired
    WelcomeController welcomeController;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        prepareUser = new PrepareUser();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void goWelcome() throws Exception {
        prepareUser.setRole("ROLE_CLIENT");
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"));
//        prepareUser.setRole("ROLE_MANAGER");
//        mockMvc.perform(get("/"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/service-panel/"));

    }

    @Test
    public void goUserPage() throws Exception {
        prepareUser.setRole("ROLE_CLIENT");
        mockMvc.perform(get("/user-page"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-page"));

    }

    @Test
    public void selectCompany() throws Exception {
        prepareUser.setRole("ROLE_MANAGER");
        mockMvc.perform(get("/user-page/select-company/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-page"));
    }

    @Test
    public void goAdminPanel() throws Exception {
        prepareUser.setRole("ROLE_MANAGER");
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));
    }

    @Test
    public void goErrorPage() throws Exception {
        mockMvc.perform(get("/403"))
                .andExpect(status().isOk())
                .andExpect(view().name("403"));;
    }


    @Test
    public void goLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

    }

    @Test
    public void logoutPage() throws Exception {
        prepareUser.setRole("ROLE_MANAGER");
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login?logout"));
    }


}