package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.CarModel;
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
public class CarModelDaoImpl implements EntityDao<CarModel> {

    private static final Logger LOGGER = Logger.getLogger(CarModelDaoImpl.class);

    private final HibernateTemplate template;

    @Autowired
    public CarModelDaoImpl(final HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public CarModel get(Long id) {
        return template.get(CarModel.class, id);
    }

    @Override
    public Long add(CarModel entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(CarModel entity) {
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
    public List<CarModel> getAll() {
        return (List<CarModel>) template.find("from CarModel");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarModel> find(String param) {
        return (List<CarModel>) template
                .findByNamedParam("from CarModel c" +
                        " where c.name like :name",
                        "name", '%' + param + '%');
    }
}
