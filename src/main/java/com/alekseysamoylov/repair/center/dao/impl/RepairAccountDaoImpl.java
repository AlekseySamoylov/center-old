package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.dao.AccountDao;
import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/5/16.
 */
@Repository
@Transactional
public class RepairAccountDaoImpl implements EntityDao<RepairAccount>, AccountDao {
    private final HibernateTemplate template;

    @Autowired
    public RepairAccountDaoImpl(final HibernateTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(readOnly = true)
    public RepairAccount get(Long id) {
        return template.get(RepairAccount.class, id);
    }

    @Override
    public Long add(RepairAccount entity) {
        return (Long) template.save(entity);
    }

    @Override
    public boolean edit(RepairAccount entity) {
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
    public List<RepairAccount> getAll() {
        return (List<RepairAccount>) template.find("from RepairAccount");
    }

    @Override
    @Transactional(readOnly = true)
    public List<RepairAccount> find(String param) {
        return (List<RepairAccount>) template
                .findByNamedParam("from RepairAccount r " +
                                "where r.name like :name",
                        "name", "%" + param + "%");
    }

    @Override
    public RepairAccount findByName(String name) {
        List<RepairAccount> list = new ArrayList<>();
        list = (List<RepairAccount>) template
                .findByNamedParam("from RepairAccount r " +
                                "where r.name like :name",
                                 "name", name);
        return list.isEmpty() ? null : list.get(0);
    }
}
