package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.OrderPart;
import com.alekseysamoylov.repair.center.model.entity.OrderRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/29/16.
 */
@Repository
@Transactional
public class OrderRatingDaoImpl implements EntityDao<OrderRating> {

    private final HibernateTemplate template;

    @Autowired
    public OrderRatingDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderRating get(Long id) {
        return template.get(OrderRating.class, id);
    }

    @Override
    public Long add(OrderRating entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(OrderRating entity) {
        template.update(entity);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        template.delete(get(id));
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderRating> getAll() {
        return (List<OrderRating>) template.find("from OrderRating");
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderRating> find(String param) {
        return (List<OrderRating>) template
                .findByNamedParam("from OrderRating o " +
                                "where o.name like :name",
                        "name", '%' + param +'%');
    }
}
