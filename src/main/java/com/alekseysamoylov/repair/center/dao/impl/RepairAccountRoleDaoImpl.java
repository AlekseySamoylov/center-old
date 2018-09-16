package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairAccountRole;
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
public class RepairAccountRoleDaoImpl implements EntityDao<RepairAccountRole> {

    private final HibernateTemplate template;

    @Autowired
    public RepairAccountRoleDaoImpl(final HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public RepairAccountRole get(Long id) {
        return template.get(RepairAccountRole.class, id);
    }

    @Override
    public Long add(RepairAccountRole entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(RepairAccountRole entity) {
        template.update(entity);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        template.delete(get(id));
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RepairAccountRole> getAll() {
        return (List<RepairAccountRole>) template
                .find("from RepairAccountRole");
    }

    //Unusable
    @Override
    @Transactional(readOnly = true)
    public List<RepairAccountRole> find(String param) {
        return null;
    }
}
