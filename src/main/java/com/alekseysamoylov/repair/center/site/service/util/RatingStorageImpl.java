package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.OrderRating;
import com.alekseysamoylov.repair.center.model.entity.RepairOrder;
import com.alekseysamoylov.repair.center.model.exceptions.UserRatingException;
import com.alekseysamoylov.repair.center.model.exceptions.WrongUserAccessException;
import com.alekseysamoylov.repair.center.site.model.element.RatingTableData;
import com.alekseysamoylov.repair.center.site.model.form.RatingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/31/16.
 */
@Service
@Transactional
public class RatingStorageImpl implements RatingStorage {

    private EntityDao<RepairOrder> orderDao;
    private EntityDao<OrderRating> ratingDao;

    @Autowired
    public RatingStorageImpl(@Qualifier("orderDaoImpl") EntityDao<RepairOrder> orderDao,
                             @Qualifier("orderRatingDaoImpl") EntityDao<OrderRating> ratingDao) {
        this.orderDao = orderDao;
        this.ratingDao = ratingDao;
    }

    @Override
    public void saveRating(RatingForm ratingForm) {
        RepairOrder repairOrder = orderDao.get(ratingForm.getId());
        OrderRating orderRating = new OrderRating();
        orderRating.setName(ratingForm.getComment());
        orderRating.setRating(ratingForm.getStars());
        orderRating.setComplete(true);
        if (repairOrder.getOrderRating() == null) {
            repairOrder.setOrderRating(orderRating);
            orderRating.setRepairOrder(repairOrder);
            ratingDao.add(orderRating);
            orderDao.edit(repairOrder);
        } else if (!repairOrder.getOrderRating().isComplete()) {
            repairOrder.setOrderRating(orderRating);
            orderRating.setRepairOrder(repairOrder);
            orderDao.edit(repairOrder);
        } else {
            throw new UserRatingException("Wrong access to setting rating from user");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<RatingTableData> getRatingData() {
        return null;
    }


}
