package com.alekseysamoylov.repair.center.site.model.json;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by alekseysamoylov on 8/7/16.
 */
public class DiagramModel {
    public String simpleDate;
    public BigDecimal daySum = BigDecimal.valueOf(0);

    @Override
    public String toString() {
        return "DiagramModel{" +
                "simpleDate='" + simpleDate + '\'' +
                ", daySum=" + daySum +
                '}';
    }
}
