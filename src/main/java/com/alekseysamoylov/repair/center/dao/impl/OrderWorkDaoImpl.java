package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.OrderWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/5/16.
 */
@Repository
@Transactional
public class OrderWorkDaoImpl implements EntityDao<OrderWork> {

    private final HibernateTemplate template;

    @Autowired
    public OrderWorkDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderWork get(Long id) {
        return template.get(OrderWork.class, id);
    }

    @Override
    public Long add(OrderWork entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(OrderWork entity) {
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
    public List<OrderWork> getAll() {
        return (List<OrderWork>) template.find("from OrderWork");
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderWork> find(String param) {
        return (List<OrderWork>) template
                .findByNamedParam("from OrderWork o where " +
                "o.name like :name",
                "name", '%' + param + '%');
    }
}
