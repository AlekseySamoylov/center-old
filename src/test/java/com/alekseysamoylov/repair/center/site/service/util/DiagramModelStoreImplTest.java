package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.AbstractTest;
import com.alekseysamoylov.repair.center.mockies.PrepareUser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.*;

/**
 * Created by alekseysamoylov on 8/7/16.
 */
public class DiagramModelStoreImplTest extends AbstractTest {

    @Autowired
    DiagramModelStore diagramModelStore;

    @Before
    public void setUp() throws Exception {
        PrepareUser prepareUser = new PrepareUser();
        prepareUser.setRole("ROLE_MANAGER");
    }

    @Test
    public void getDiagramModel() throws Exception {
        System.out.println(diagramModelStore.getDiagramModels());
    }

}