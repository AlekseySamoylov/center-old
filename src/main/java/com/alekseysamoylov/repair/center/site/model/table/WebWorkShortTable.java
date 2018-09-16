package com.alekseysamoylov.repair.center.site.model.table;

import com.alekseysamoylov.repair.center.site.model.element.WebWorkShort;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
public class WebWorkShortTable extends ParentTable<WebWorkShort> {
    public WebWorkShortTable(List<WebWorkShort> webList) {
        this.webList = webList;
    }
}
