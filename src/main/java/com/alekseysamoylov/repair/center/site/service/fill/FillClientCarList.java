package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.AccountCar;
import com.alekseysamoylov.repair.center.site.model.WebTable;
import com.alekseysamoylov.repair.center.site.model.element.WebClientCar;
import com.alekseysamoylov.repair.center.site.model.table.WebClientCarTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
@Service
@Transactional
public class FillClientCarList implements FillWebList<WebClientCar> {

    private final EntityDao<AccountCar> entityDao;

    @Autowired
    public FillClientCarList(@Qualifier("accountCarDaoImpl") EntityDao<AccountCar> entityDao) {
        this.entityDao = entityDao;
    }

    /**
     *
     * @param id clientId
     * @return Client's cars
     */
    @Override
    @Transactional(readOnly = true)
    public WebTable<WebClientCar> getFilledWebList(Long id) {
        List<WebClientCar> webList = new ArrayList<>();
        entityDao.getAll().stream()
                .filter(entity->entity.getRepairAccount()
                        .getId().equals(id)).forEach(entity->{
            WebClientCar element = new WebClientCar();
            element.setId(entity.getId());
            element.setName(
                    entity.getCarModel().getCarMark().getName()
                            + " " + entity.getCarModel().getName()
                            + " " + entity.getName());
            webList.add(element);
        });
        return new WebClientCarTable(webList);
    }


    /**
     * Unusable
     * @return null
     */
    @Override
    public WebTable<WebClientCar> getFilledWebList() {
        return null;
    }
}
