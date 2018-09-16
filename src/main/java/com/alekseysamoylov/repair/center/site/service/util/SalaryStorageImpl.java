package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.AccountByCompany;
import com.alekseysamoylov.repair.center.site.model.json.SalaryEmployee;
import com.alekseysamoylov.repair.center.site.model.json.SalaryWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alekseysamoylov on 8/6/16.
 */
@Service
@Transactional
public class SalaryStorageImpl implements SalaryStorage {

    private final AccountByCompany accountByCompany;

    @Autowired
    public SalaryStorageImpl(AccountByCompany accountByCompany) {
        this.accountByCompany = accountByCompany;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalaryEmployee> getSalaryEmployee() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<SalaryEmployee> salaryEmployees = new ArrayList<>();
        accountByCompany.getAllMasters().forEach(dbMaster -> {
            SalaryEmployee employee = new SalaryEmployee();
            //set Name
            employee.name = dbMaster.getAccountProperty().getName() + " " +
                    dbMaster.getAccountProperty().getLastName();
            //set Works
            List<SalaryWork> salaryWorks = new ArrayList<>();
            dbMaster.getMasterOrderWorks().stream()
                    .filter(orderWork ->
                            simpleDateFormat.format(orderWork.getRepairOrder()
                                    .getOrderDate())
                                    .equals(simpleDateFormat.format(new Date())))
                    .forEach(orderWork -> {
                        SalaryWork salaryWork = new SalaryWork();
                        salaryWork.name = orderWork.getName();
                        salaryWork.price = orderWork.getWorkSum();
                        salaryWorks.add(salaryWork);
                    });
            employee.works = salaryWorks;
            salaryEmployees.add(employee);
        });
        return salaryEmployees;
    }
}
