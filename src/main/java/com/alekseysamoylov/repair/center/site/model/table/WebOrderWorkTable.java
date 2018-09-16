package com.alekseysamoylov.repair.center.site.model.table;

import com.alekseysamoylov.repair.center.site.model.element.WebOrderWork;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/8/16.
 */
public class WebOrderWorkTable extends ParentTable<WebOrderWork> {
    public WebOrderWorkTable(List<WebOrderWork> webList) {
        this.webList = webList;
    }
}
