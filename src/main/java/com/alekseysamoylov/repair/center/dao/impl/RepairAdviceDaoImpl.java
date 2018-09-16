package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairAdvice;
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
public class RepairAdviceDaoImpl implements EntityDao<RepairAdvice> {

    private final HibernateTemplate template;

    @Autowired
    public RepairAdviceDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public RepairAdvice get(Long id) {
        return template.get(RepairAdvice.class, id);
    }

    @Override
    public Long add(RepairAdvice entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(RepairAdvice entity) {
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
    public List<RepairAdvice> getAll() {
        return (List<RepairAdvice>) template.find("from RepairAdvice");
    }

    @Override
    @Transactional(readOnly = true)
    public List<RepairAdvice> find(String param) {
        return (List<RepairAdvice>) template
                .findByNamedParam("from RepairAdvice r " +
                        "where r.adviceText like :name",
                        "name", '%' + param + '%');
    }
}
