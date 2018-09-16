package com.alekseysamoylov.repair.center.site.model.table;

import com.alekseysamoylov.repair.center.site.model.element.WebClientCar;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
public class WebClientCarTable extends ParentTable<WebClientCar> {

    public WebClientCarTable(List<WebClientCar> list){
        this.webList = list;
    }
}
