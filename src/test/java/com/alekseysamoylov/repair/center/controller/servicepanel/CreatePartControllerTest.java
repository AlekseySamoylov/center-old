package com.alekseysamoylov.repair.center.controller.servicepanel;

import com.alekseysamoylov.repair.center.AbstractTest;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderPartTemplate;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderTemplate;
import com.alekseysamoylov.repair.center.site.service.util.WebPartStorage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alekseysamoylov on 7/26/16.
 */
public class CreatePartControllerTest extends AbstractTest {

    @Mock
    WebPartStorage webPartStorage;

    @Mock
    HttpServletRequest request;

    @InjectMocks
    CreatePartController createPartController;

    private MockMvc mockMvc;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(createPartController).build();
        mockMvc.perform(post("/service-panel/open/part")
                .param("orderId", "1")
                .param("name", "Part")
                .param("price", "200")
                .param("value", "2"));
    }


    @Test
    public void isManager() throws Exception {
        Assert.notNull(webPartStorage);
        Assert.notNull(request);
    }

    @Test
    public void savePart() throws Exception {
        Mockito.mock(HttpServletResponse.class);

        WebOrderPartTemplate partTemplate = new WebOrderPartTemplate();
        partTemplate.setName("Part");
        partTemplate.setOrderId(1L);
        partTemplate.setPrice(BigDecimal.valueOf(200));
        partTemplate.setValue(2);
        ArgumentCaptor<WebOrderPartTemplate> argument = ArgumentCaptor.forClass(WebOrderPartTemplate.class);
        verify(webPartStorage).saveOrderPart(argument.capture());
        assertEquals("Part", argument.getValue().getName());

        Mockito.doNothing().when(webPartStorage).saveOrderPart(anyObject());

        mockMvc.perform(MockMvcRequestBuilders.get("/service-panel/open/part"))
                .andExpect(status().is3xxRedirection());
    }


}