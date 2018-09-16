package com.alekseysamoylov.repair.center.site.model.table;

import com.alekseysamoylov.repair.center.site.model.element.WebOrder;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/6/16.
 */
public class WebOrderTable extends ParentTable<WebOrder> {

    public WebOrderTable(List<WebOrder> orderList) {
        this.webList = orderList;
    }

}
