package com.alekseysamoylov.repair.center.site.model.table;

import com.alekseysamoylov.repair.center.site.model.WebTable;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderPart;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/8/16.
 */
public class WebOrderPartTable extends ParentTable<WebOrderPart> {
    public WebOrderPartTable(List<WebOrderPart> webList) {
        this.webList = webList;
    }
}
