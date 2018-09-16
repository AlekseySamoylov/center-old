package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.Company;
import com.alekseysamoylov.repair.center.model.entity.OrderPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
@Repository
@Transactional
public class CompanyDaoImpl implements EntityDao<Company> {
    private final HibernateTemplate template;

    @Autowired
    public CompanyDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public Company get(Long id) {
        return template.get(Company.class, id);
    }

    @Override
    public Long add(Company entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(Company entity) {
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
    public List<Company> getAll() {
        return (List<Company>) template.find("from Company");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> find(String param) {
        return (List<Company>) template
                .findByNamedParam("from Company o " +
                                "where o.name like :name",
                        "name", '%' + param +'%');
    }
}
