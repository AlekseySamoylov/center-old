package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.PurchaseLog;
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
public class PurchaseLogDaoImpl implements EntityDao<PurchaseLog> {

    private final HibernateTemplate template;

    @Autowired
    public PurchaseLogDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseLog get(Long id) {
        return template.get(PurchaseLog.class, id);
    }

    @Override
    public Long add(PurchaseLog entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(PurchaseLog entity) {
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
    public List<PurchaseLog> getAll() {
        return (List<PurchaseLog>) template.find("from PurchaseLog");
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseLog> find(String param) {
        return (List<PurchaseLog>) template
                .findByNamedParam("from PurchaseLog r " +
                                "where r.name like :name",
                        "name", '%' + param + '%');
    }
}