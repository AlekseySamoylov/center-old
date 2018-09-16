package com.alekseysamoylov.repair.center.site.model.json;

import java.math.BigDecimal;

/**
 * Created by alekseysamoylov on 8/6/16.
 */
public class SalaryWork {
    public String name;
    public BigDecimal price;

    @Override
    public String toString() {
        return "SalaryWork{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
