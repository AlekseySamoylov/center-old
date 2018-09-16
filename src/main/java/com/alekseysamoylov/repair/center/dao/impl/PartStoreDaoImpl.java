package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.PartStore;
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
public class PartStoreDaoImpl implements EntityDao<PartStore> {

    private final HibernateTemplate template;

    @Autowired
    public PartStoreDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public PartStore get(Long id) {
        return template.get(PartStore.class, id);
    }

    @Override
    public Long add(PartStore entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(PartStore entity) {
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
    public List<PartStore> getAll() {
        return (List<PartStore>) template.find("from PartStore");
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartStore> find(String param) {
        return (List<PartStore>) template
                .findByNamedParam("from PartStore r " +
                                "where r.name like :name",
                        "name", '%' + param + '%');
    }
}