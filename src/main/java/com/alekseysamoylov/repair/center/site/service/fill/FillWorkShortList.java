package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairSection;
import com.alekseysamoylov.repair.center.site.model.WebTable;
import com.alekseysamoylov.repair.center.site.model.element.WebWorkShort;
import com.alekseysamoylov.repair.center.site.model.table.WebWorkShortTable;
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
public class FillWorkShortList implements FillWebList<WebWorkShort> {

    private final EntityDao<RepairSection> sectionDao;

    @Autowired
    public FillWorkShortList(@Qualifier("repairSectionDaoImpl") EntityDao<RepairSection> sectionDao) {
        this.sectionDao = sectionDao;
    }

    /**
     *
     * @param id SectionId
     * @return
     */
    @Override
    @Transactional
    public WebTable<WebWorkShort> getFilledWebList(Long id) {
        List<WebWorkShort> webList = new ArrayList<>();
        RepairSection section = sectionDao.get(id);
        section.getRepairPrices().forEach(databaseWork->{
            //FIX it!!!
            if(databaseWork.getRepairAccount().getId().equals(1L)){
                WebWorkShort webWork = new WebWorkShort();
                webWork.setId(databaseWork.getId());
                webWork.setName(databaseWork.getName());
                webList.add(webWork);
            }
        });
        return new WebWorkShortTable(webList);
    }

    /**
     * unusable
     * @return null
     */
    @Override
    public WebTable<WebWorkShort> getFilledWebList() {
        return null;
    }
}
