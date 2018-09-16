package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.CarMark;
import com.alekseysamoylov.repair.center.model.entity.CarModel;
import com.alekseysamoylov.repair.center.site.model.WebTable;
import com.alekseysamoylov.repair.center.site.model.element.WebCarMark;
import com.alekseysamoylov.repair.center.site.model.element.WebCarModel;
import com.alekseysamoylov.repair.center.site.model.table.WebCarMarkTable;
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
public class FillCarMarkList implements FillWebList<WebCarMark> {

    private final EntityDao<CarMark> entityDao;

    @Autowired
    public FillCarMarkList(@Qualifier("carMarkDaoImpl") EntityDao<CarMark> entityDao) {
        this.entityDao = entityDao;
    }

    @Override
    @Transactional(readOnly = true)
    public WebTable<WebCarMark> getFilledWebList() {
        List<WebCarMark> webList = new ArrayList<>();
        for (CarMark databaseCarMark : entityDao.getAll()) {
            WebCarMark webCarMark = new WebCarMark();
            webCarMark.setId(databaseCarMark.getId());
            webCarMark.setName(databaseCarMark.getName());
            for (CarModel databaseCarModel : databaseCarMark.getModels()) {
                WebCarModel webCarModel = new WebCarModel();
                webCarModel.setId(databaseCarModel.getId());
                webCarModel.setName(databaseCarModel.getName());
                webCarMark.getModels().add(webCarModel);
            }
            webList.add(webCarMark);
        }
        return new WebCarMarkTable(webList);
    }

    /**
     * Unusable
     * @return null
     */
    @Override
    public WebTable<WebCarMark> getFilledWebList(Long id) {
        return null;
    }
}
