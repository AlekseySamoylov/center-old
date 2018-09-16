package com.alekseysamoylov.repair.center.controller.servicepanel;

import com.alekseysamoylov.repair.center.site.model.json.DiagramModel;
import com.alekseysamoylov.repair.center.site.service.util.DiagramModelStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 8/7/16.
 */
@Controller
public class DiagramController {

    private final DiagramModelStore diagramModelStore;

    @Autowired
    public DiagramController(DiagramModelStore diagramModelStore) {
        this.diagramModelStore = diagramModelStore;
    }

    @RequestMapping(value = "/diagrams")
    @ResponseBody
    public List<DiagramModel> getDiagramData() {
        return diagramModelStore.getDiagramModels();
    }

    @CrossOrigin
    @RequestMapping("/diagrams-test")
    @ResponseBody
    public List<DiagramModel> getDiagramTest() {
        List<DiagramModel> diagramModelList = new ArrayList<>();
        DiagramModel diagramModel = new DiagramModel();
        diagramModel.simpleDate = "02 11 2001";
        diagramModel.daySum = BigDecimal.valueOf(8500.00);
        diagramModelList.add(diagramModel);
        diagramModel = new DiagramModel();
        diagramModel.simpleDate = "08 28 2001";
        diagramModel.daySum = BigDecimal.valueOf(5500.00);
        diagramModelList.add(diagramModel);
        diagramModel = new DiagramModel();
        diagramModel.simpleDate = "05 22 2002";
        diagramModel.daySum = BigDecimal.valueOf(5500.00);
        diagramModelList.add(diagramModel);
        return diagramModelList;
    }
}
