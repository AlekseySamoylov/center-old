package com.alekseysamoylov.repair.center.controller.client;

import com.alekseysamoylov.repair.center.AbstractTest;
import com.alekseysamoylov.repair.center.site.model.form.RatingForm;
import com.alekseysamoylov.repair.center.site.service.util.RatingStorage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alekseysamoylov on 7/31/16.
 */
public class RatingControllerTest extends AbstractTest {

    MockMvc mockMvc;

    @Mock
    RatingStorage ratingStorage;

    @InjectMocks
    RatingController ratingController;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();
    }

    @Test
    public void goToGetRating() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/service-panel/rating/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("client-rating"));
    }

    @Test
    public void saveRating() throws Exception {

        RatingForm ratingForm = new RatingForm();
        ratingForm.setId(1L);
        ratingForm.setStars(4);

        doNothing().when(ratingStorage).saveRating(anyObject());
        mockMvc.perform(post("/rating/save")
                .param("id", "1")
                .param("stars", "4")
                .param("comment", "All ok"))
                .andExpect(status().isOk());
        Mockito.verify(ratingStorage).saveRating(ratingForm);


    }
}