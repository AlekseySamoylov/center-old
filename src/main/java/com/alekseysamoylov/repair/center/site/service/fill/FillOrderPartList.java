package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.OrderPart;
import com.alekseysamoylov.repair.center.site.model.WebTable;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderPart;
import com.alekseysamoylov.repair.center.site.model.table.WebOrderPartTable;
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
public class FillOrderPartList implements FillWebList<WebOrderPart> {

    private final EntityDao<OrderPart> orderPartDao;

    @Autowired
    public FillOrderPartList(@Qualifier("orderPartDaoImpl") EntityDao<OrderPart> orderPartDao) {
        this.orderPartDao = orderPartDao;
    }

    /**
     *
     * @param id orderId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public WebTable<WebOrderPart> getFilledWebList(Long id) {
        List<WebOrderPart> webList = new ArrayList<>();
        orderPartDao.getAll().stream()
                .filter(databasePart->databasePart.getRepairOrder()
                        .getId().equals(id)).forEach(databasePart-> {
            WebOrderPart webPart = new WebOrderPart();
            webPart.setId(databasePart.getId());
            webPart.setName(databasePart.getName());
            webPart.setPrice(databasePart.getOrderPartPrice());
            webPart.setValue(databasePart.getOrderPartValue());
            webPart.setSum(databasePart.getOrderPartSum());
            webList.add(webPart);
        });
        return new WebOrderPartTable(webList);
    }

    /**
     * Unusable
     * @return null
     */
    @Override
    public WebTable<WebOrderPart> getFilledWebList() {
        return null;
    }


}
