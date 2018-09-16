package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.OrderWork;
import com.alekseysamoylov.repair.center.site.model.WebTable;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderWork;
import com.alekseysamoylov.repair.center.site.model.table.WebOrderWorkTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/8/16.
 */
@Service
@Transactional
public class FillOrderWorkList implements FillWebList<WebOrderWork> {

    private final EntityDao<OrderWork> orderWorkDao;

    @Autowired
    public FillOrderWorkList(@Qualifier("orderWorkDaoImpl") EntityDao<OrderWork> orderWorkDao) {
        this.orderWorkDao = orderWorkDao;
    }

    /**
     *
     * @param id orderId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public WebTable<WebOrderWork> getFilledWebList(Long id) {
        List<WebOrderWork> webOrderWorks = new ArrayList<>();

        orderWorkDao.getAll().stream()
                .filter(databaseWork->databaseWork
                        .getRepairOrder().getId().equals(id))
                .forEach(databaseWork->{
                    WebOrderWork webWork = new WebOrderWork();
                    webWork.setId(databaseWork.getId());
                    webWork.setMaster(databaseWork.getMaster()
                            .getAccountProperty().getLastName() + " "
                            + databaseWork.getMaster()
                            .getAccountProperty().getName());
                    webWork.setName(databaseWork.getName());
                    webWork.setPrice(databaseWork.getWorkPrice());
                    webWork.setValue(databaseWork.getWorkValue());
                    webWork.setSum(databaseWork.getWorkSum());
                    webOrderWorks.add(webWork);
                });
        return new WebOrderWorkTable(webOrderWorks);
    }

    /**
     * Unusable
     *
     * @return null
     */
    @Override
    public WebTable<WebOrderWork> getFilledWebList() {
        return null;
    }


}
