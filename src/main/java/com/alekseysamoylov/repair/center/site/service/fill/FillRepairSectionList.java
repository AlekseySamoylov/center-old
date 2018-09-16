package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairSection;
import com.alekseysamoylov.repair.center.site.model.WebTable;
import com.alekseysamoylov.repair.center.site.model.element.WebRepairSection;
import com.alekseysamoylov.repair.center.site.model.table.WebRepairSectionTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
@Service
@Transactional
public class FillRepairSectionList implements FillWebList<WebRepairSection> {

   private final EntityDao<RepairSection> sectionDao;

    @Autowired
    public FillRepairSectionList(@Qualifier("repairSectionDaoImpl") EntityDao<RepairSection> sectionDao) {
        this.sectionDao = sectionDao;
    }

    @Override
    @Transactional(readOnly = true)
    public WebTable<WebRepairSection> getFilledWebList() {
        List<WebRepairSection> webList = new ArrayList<>();
        sectionDao.getAll().forEach(databaseSection->{
            WebRepairSection webSection = new WebRepairSection();
            webSection.setId(databaseSection.getId());
            webSection.setName(databaseSection.getName());
            webList.add(webSection);
        });
        return new WebRepairSectionTable(webList);
    }

    /**
     * unusable
     * @param id unusable
     * @return null
     */
    @Override
    public WebTable<WebRepairSection> getFilledWebList(Long id) {
        return null;

    }
}
