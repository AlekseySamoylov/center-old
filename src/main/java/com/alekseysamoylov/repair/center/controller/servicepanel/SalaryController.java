package com.alekseysamoylov.repair.center.controller.servicepanel;

import com.alekseysamoylov.repair.center.site.model.json.SalaryEmployee;
import com.alekseysamoylov.repair.center.site.service.util.SalaryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by alekseysamoylov on 8/6/16.
 */
@Controller
public class SalaryController {

    private final SalaryStorage salaryStorage;

    @Autowired
    public SalaryController(SalaryStorage salaryStorage) {
        this.salaryStorage = salaryStorage;
    }

    @RequestMapping("/service-panel/salary")
    @ResponseBody
    public List<SalaryEmployee> goSalary() {
        return salaryStorage.getSalaryEmployee();
    }

    @RequestMapping("/service-panel/salary-page")
    public String goSalaryPage() {
        return "salary";
    }
}
