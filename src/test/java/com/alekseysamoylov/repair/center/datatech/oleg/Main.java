package com.alekseysamoylov.repair.center.datatech.oleg;

import com.alekseysamoylov.repair.center.datatech.oleg.context.TestContextDatatech;
import com.alekseysamoylov.repair.center.datatech.oleg.service.ServiceOleg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by alekseysamoylov on 8/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContextDatatech.class)
public class Main {

    @Autowired
    ServiceOleg serviceOleg;

    @Test
    public void testOleg() {
        serviceOleg.Hi();
    }
}
