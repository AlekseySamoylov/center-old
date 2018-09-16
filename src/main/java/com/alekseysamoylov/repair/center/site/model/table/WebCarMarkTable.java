package com.alekseysamoylov.repair.center.site.model.table;

import com.alekseysamoylov.repair.center.site.model.element.WebCarMark;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
public class WebCarMarkTable extends ParentTable<WebCarMark> {
    public WebCarMarkTable(List<WebCarMark> webList) {
        this.webList = webList;
    }
}
