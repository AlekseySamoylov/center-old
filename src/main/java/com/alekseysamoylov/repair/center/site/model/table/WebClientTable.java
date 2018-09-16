package com.alekseysamoylov.repair.center.site.model.table;

import com.alekseysamoylov.repair.center.site.model.element.WebClient;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
public class WebClientTable extends ParentTable<WebClient> {

    public WebClientTable(List<WebClient> webList) {
        this.webList = webList;
    }
}
