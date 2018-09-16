package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.CarMark;
import org.apache.log4j.Logger;
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
public class CarMarkDaoImpl implements EntityDao<CarMark> {

    private static final Logger LOGGER = Logger.getLogger(CarMarkDaoImpl.class);

    private final HibernateTemplate template;

    @Autowired
    public CarMarkDaoImpl(final HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public CarMark get(Long id) {
        return template.get(CarMark.class, id);
    }

    @Override
    public Long add(CarMark entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(CarMark entity) {
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
    public List<CarMark> getAll() {
        return (List<CarMark>) template.find("from CarMark");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarMark> find(String param) {
        return (List<CarMark>) template
                .findByNamedParam("from CarMark c " +
                        "where c.name like :name",
                        "name", '%' + param + '%');
    }
}
