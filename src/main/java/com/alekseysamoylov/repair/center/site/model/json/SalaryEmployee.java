package com.alekseysamoylov.repair.center.site.model.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 8/6/16.
 */
public class SalaryEmployee {
    public String name;
    public List<SalaryWork> works = new ArrayList<>();

    @Override
    public String toString() {
        return "SalaryEmployee{" +
                "name='" + name + '\'' +
                ", works=" + works +
                '}';
    }
}
