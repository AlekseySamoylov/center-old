package com.alekseysamoylov.repair.center.site.model.table;

import com.alekseysamoylov.repair.center.site.model.element.WebRepairSection;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
public class WebRepairSectionTable extends ParentTable<WebRepairSection> {
    public WebRepairSectionTable(List<WebRepairSection> webList) {
        this.webList = webList;
    }
}
