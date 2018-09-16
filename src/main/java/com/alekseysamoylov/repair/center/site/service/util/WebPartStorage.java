package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.OrderPart;
import com.alekseysamoylov.repair.center.model.entity.RepairOrder;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderPartTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by alekseysamoylov on 7/10/16.
 */
@Service
@Transactional
public class WebPartStorage {

    private final EntityDao<OrderPart> orderPartDao;

    private final EntityDao<RepairOrder> orderDao;

    @Autowired
    public WebPartStorage(@Qualifier("orderPartDaoImpl") EntityDao<OrderPart> orderPartDao, @Qualifier("orderDaoImpl") EntityDao<RepairOrder> orderDao) {
        this.orderPartDao = orderPartDao;
        this.orderDao = orderDao;
    }

    public void saveOrderPart(WebOrderPartTemplate partTemplate) {
        RepairOrder repairOrder = orderDao.get(partTemplate.getOrderId());
        OrderPart orderPart = new OrderPart();
        orderPart.setRepairOrder(repairOrder);
        orderPart.setName(partTemplate.getName());
        orderPart.setOrderPartPrice(partTemplate.getPrice());
        orderPart.setOrderPartValue(partTemplate.getValue());
        orderPart.setOrderPartSum(partTemplate.getPrice()
                .multiply(BigDecimal.valueOf(partTemplate.getValue())));
        orderPartDao.add(orderPart);
    }

    public void deleteOrderPart(Long partId) {
        orderPartDao.delete(partId);
    }
}
