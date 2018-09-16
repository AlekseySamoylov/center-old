package com.alekseysamoylov.repair.center.site.model.table;

import com.alekseysamoylov.repair.center.site.model.element.WebWorkPrice;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
public class WebWorkPriceTable extends ParentTable<WebWorkPrice> {
    public WebWorkPriceTable(List<WebWorkPrice> webList) {
        this.webList = webList;
    }
}
