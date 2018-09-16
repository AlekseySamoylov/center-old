package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.site.model.json.DiagramModel;

import java.util.List;

/**
 * Created by alekseysamoylov on 8/7/16.
 */
public interface DiagramModelStore {
    /**
     * Get Diagram models for current company
     * @return diagram models
     */
    List<DiagramModel> getDiagramModels();
}
