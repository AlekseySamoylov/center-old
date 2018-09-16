package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.OrderWork;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
import com.alekseysamoylov.repair.center.model.entity.RepairOrder;
import com.alekseysamoylov.repair.center.model.entity.RepairPrice;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderWorkTemplate;
import com.alekseysamoylov.repair.center.site.model.element.WebWorkSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
@Service
@Transactional
public class WebOrderWorkStorage {

    private final EntityDao<RepairAccount> accountDao;

    private final EntityDao<RepairPrice> priceDao;

    private final EntityDao<RepairOrder> orderDao;

    private final EntityDao<OrderWork> orderWorkDao;

    @Autowired
    public WebOrderWorkStorage(@Qualifier("orderDaoImpl") EntityDao<RepairOrder> orderDao, @Qualifier("orderWorkDaoImpl") EntityDao<OrderWork> orderWorkDao, @Qualifier("repairAccountDaoImpl") EntityDao<RepairAccount> accountDao, @Qualifier("repairPriceDaoImpl") EntityDao<RepairPrice> priceDao) {
        this.orderDao = orderDao;
        this.orderWorkDao = orderWorkDao;
        this.accountDao = accountDao;
        this.priceDao = priceDao;
    }


    @Transactional(readOnly = true)
    public WebWorkSuggestion getWorkSuggestions(Long workId, Long carModelId) {
        RepairPrice repairPrice = priceDao.get(workId);
        WebWorkSuggestion suggestion = new WebWorkSuggestion();
        suggestion.setWorkName(repairPrice.getName());
        return suggestion;
    }

    @Transactional
    public void saveWork(WebOrderWorkTemplate workTemplate) {
        OrderWork orderWork = new OrderWork();
        orderWork.setRepairOrder(orderDao.get(workTemplate.getOrderId()));
        orderWork.setMaster(accountDao.get(workTemplate.getMasterId()));
//        if (workTemplate.isWorkInPrice()) {
//            orderWork.setName(priceDao.get(workTemplate.getPriceWorkId()).getName());
//        } else {
            orderWork.setName(workTemplate.getPriceName());
//        }
        orderWork.setWorkPrice(workTemplate.getPriceCost());
        orderWork.setWorkValue(workTemplate.getPriceValue());
        orderWork.setWorkSum(workTemplate.getPriceCost().multiply(BigDecimal.valueOf(workTemplate.getPriceValue())));
        orderWorkDao.add(orderWork);
    }

    @Transactional
    public void deleteOrderWork(Long id) {
        orderWorkDao.delete(id);
    }

}
