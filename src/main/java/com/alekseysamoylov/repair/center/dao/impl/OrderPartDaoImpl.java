package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.OrderPart;
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
public class OrderPartDaoImpl implements EntityDao<OrderPart> {

    private final HibernateTemplate template;

    @Autowired
    public OrderPartDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderPart get(Long id) {
        return template.get(OrderPart.class, id);
    }

    @Override
    public Long add(OrderPart entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(OrderPart entity) {
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
    public List<OrderPart> getAll() {
        return (List<OrderPart>) template.find("from OrderPart");
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderPart> find(String param) {
        return (List<OrderPart>) template
                .findByNamedParam("from OrderPart o " +
                        "where o.name like :name",
                        "name", '%' + param +'%');
    }
}
