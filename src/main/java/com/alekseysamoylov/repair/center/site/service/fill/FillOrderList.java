package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairOrder;
import com.alekseysamoylov.repair.center.site.model.WebTable;
import com.alekseysamoylov.repair.center.site.model.element.WebOrder;
import com.alekseysamoylov.repair.center.site.model.table.WebOrderTable;
import com.alekseysamoylov.repair.center.site.service.util.WebOrderStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alekseysamoylov on 7/6/16.
 */
@Service
@Transactional
public class FillOrderList implements FillWebList<WebOrder> {

      private final WebOrderStorage webOrderStorage;

    @Autowired
    public FillOrderList(WebOrderStorage webOrderStorage) {
        this.webOrderStorage = webOrderStorage;
    }

    @Override
    @Transactional(readOnly = true)
    public WebTable<WebOrder> getFilledWebList() {
        return new WebOrderTable(webOrderStorage.getAllWebOrders());
    }

    /**
     * USE IT!!!
     * @param id Company id
     * @return list for one compant
     */
    @Override
    @Transactional(readOnly = true)
    public WebTable<WebOrder> getFilledWebList(Long id) {
        return new WebOrderTable(webOrderStorage
                .getAllWebOrders().stream()
                .filter(webOrder->id.equals(webOrder
                        .getWebCompany().getId()))
                                .collect(Collectors.toList()));
    }

}
