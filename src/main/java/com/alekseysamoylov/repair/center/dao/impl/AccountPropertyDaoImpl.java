package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.AccountProperty;
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
public class AccountPropertyDaoImpl implements EntityDao<AccountProperty> {

    private final HibernateTemplate template;

    @Autowired
    public AccountPropertyDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public AccountProperty get(Long id) {
        return template.get(AccountProperty.class, id);
    }

    /**
     * You have to save account before and
     * add saved account to AccountProperty POJO
     * @param entity !With saved account!
     * @return id
     */
    @Override
    public Long add(AccountProperty entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(AccountProperty entity) {
        template.update(entity);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        template.delete(get(id));
        return true;
    }

    @Override
    public List<AccountProperty> getAll() {
        return (List<AccountProperty>) template.find("from AccountProperty");
    }

    @Override
    public List<AccountProperty> find(String param) {
        return (List<AccountProperty>) template
                .findByNamedParam("from AccountProperty" +
                        " ac where ac.lastName like :name",
                        "name", '%' + param + '%');
    }
}
