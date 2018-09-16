package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairPrice;
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
public class RepairPriceDaoImpl implements EntityDao<RepairPrice> {

    private final HibernateTemplate template;

    @Autowired
    public RepairPriceDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public RepairPrice get(Long id) {
        return template.get(RepairPrice.class, id);
    }

    @Override
    public Long add(RepairPrice entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(RepairPrice entity) {
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
    public List<RepairPrice> getAll() {
        return (List<RepairPrice>) template.find("from RepairPrice");
    }

    @Override
    @Transactional(readOnly = true)
    public List<RepairPrice> find(String param) {
        return (List<RepairPrice>) template
                .findByNamedParam("from RepairPrice r " +
                                "where r.name like :name",
                        "name", '%' + param + '%');
    }
}