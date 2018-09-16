package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.PartType;
import com.alekseysamoylov.repair.center.model.entity.PartType;
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
public class PartTypeDaoImpl implements EntityDao<PartType> {

    private final HibernateTemplate template;

    @Autowired
    public PartTypeDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public PartType get(Long id) {
        return template.get(PartType.class, id);
    }

    @Override
    public Long add(PartType entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(PartType entity) {
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
    public List<PartType> getAll() {
        return (List<PartType>) template.find("from PartType");
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartType> find(String param) {
        return (List<PartType>) template
                .findByNamedParam("from PartType r " +
                                "where r.name like :name",
                        "name", '%' + param + '%');
    }
}
