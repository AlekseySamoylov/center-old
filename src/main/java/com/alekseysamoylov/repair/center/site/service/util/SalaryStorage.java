package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.site.model.json.SalaryEmployee;

import java.util.List;

/**
 * Created by alekseysamoylov on 8/6/16.
 * Getting salary for View about workers
 */
public interface SalaryStorage {

    /**
     *
     * @return List of SalaryEmployee
     */
    List<SalaryEmployee> getSalaryEmployee();
}
