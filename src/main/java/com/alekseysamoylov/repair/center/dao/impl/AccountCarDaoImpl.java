package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.AccountCar;
import com.alekseysamoylov.repair.center.model.entity.RepairPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/6/16.
 */
@Repository
@Transactional
public class AccountCarDaoImpl implements EntityDao<AccountCar> {

    private final HibernateTemplate template;

    @Autowired
    public AccountCarDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountCar get(Long id) {
        return template.get(AccountCar.class, id);
    }

    @Override
    public Long add(AccountCar entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(AccountCar entity) {
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
    public List<AccountCar> getAll() {
        return (List<AccountCar>) template.find("from AccountCar");
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountCar> find(String param) {
        return (List<AccountCar>) template
                .findByNamedParam("from AccountCar r " +
                                "where r.name like :name",
                        "name", '%' + param + '%');
    }
}