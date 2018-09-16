package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairSection;
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
public class RepairSectionDaoImpl implements EntityDao<RepairSection> {

    private final HibernateTemplate template;

    @Autowired
    public RepairSectionDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public RepairSection get(Long id) {
        return template.get(RepairSection.class, id);
    }

    @Override
    public Long add(RepairSection entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(RepairSection entity) {
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
    public List<RepairSection> getAll() {
        return (List<RepairSection>) template
                .find("from RepairSection");
    }

    @Override
    @Transactional(readOnly = true)
    public List<RepairSection> find(String param) {
        return (List<RepairSection>) template
                .findByNamedParam("from RepairSection r " +
                        "where r.name like :name",
                        "name", '%' + param + '%');
    }
}
