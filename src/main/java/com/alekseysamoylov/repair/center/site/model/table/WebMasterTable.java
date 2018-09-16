package com.alekseysamoylov.repair.center.site.model.table;

import com.alekseysamoylov.repair.center.site.model.element.WebMaster;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
public class WebMasterTable extends ParentTable<WebMaster> {
    public WebMasterTable(List<WebMaster> webList) {
        this.webList = webList;
    }
}
