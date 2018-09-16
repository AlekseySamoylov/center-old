package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
import com.alekseysamoylov.repair.center.model.entity.RepairOrder;
import com.alekseysamoylov.repair.center.service.MyConfiguration;
import com.alekseysamoylov.repair.center.site.model.WebTable;
import com.alekseysamoylov.repair.center.site.model.element.WebMaster;
import com.alekseysamoylov.repair.center.site.model.table.WebMasterTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
@Service
@Transactional
public class FillMasterList implements FillWebList<WebMaster> {

    private final MyConfiguration configuration;

    private final EntityDao<RepairOrder> orderDao;

    private final EntityDao<RepairAccount> accountDao;

    @Autowired
    public FillMasterList(@Qualifier("orderDaoImpl") EntityDao<RepairOrder> orderDao, @Qualifier("repairAccountDaoImpl") EntityDao<RepairAccount> accountDao, MyConfiguration configuration) {
        this.orderDao = orderDao;
        this.accountDao = accountDao;
        this.configuration = configuration;
    }

    /**
     *
     * @param id orderId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public WebTable<WebMaster> getFilledWebList(Long id) {
        List<WebMaster> webList = new ArrayList<>();
        RepairOrder repairOrder = orderDao.get(id);
        repairOrder.getCompany().getMembers()
                .stream().filter(master->master
                .getRepairAccountRole().getId()
                .equals(configuration.getMasterRoleId()))
                .forEach(databaseMaster->{
                    WebMaster webMaster = new WebMaster();
                    webMaster.setId(databaseMaster.getId());
                    webMaster.setName(databaseMaster
                            .getAccountProperty().getLastName() + " "
                    + databaseMaster.getAccountProperty().getName());
                    webList.add(webMaster);
                });
        return new WebMasterTable(webList);
    }

    /**
     * unusable
     * @return null
     */
    @Override
    public WebTable<WebMaster> getFilledWebList() {
        return null;
    }

}
