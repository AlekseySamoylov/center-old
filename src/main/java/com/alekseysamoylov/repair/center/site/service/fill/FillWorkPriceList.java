package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.RepairPrice;
import com.alekseysamoylov.repair.center.site.model.WebTable;
import com.alekseysamoylov.repair.center.site.model.element.WebWorkPrice;
import com.alekseysamoylov.repair.center.site.model.table.WebWorkPriceTable;
import org.apache.log4j.Logger;
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
public class FillWorkPriceList implements FillWebList<WebWorkPrice> {

    private final Logger LOGGER = Logger.getLogger(FillWorkPriceList.class);
    private final EntityDao<RepairPrice> priceDao;

    @Autowired
    public FillWorkPriceList(@Qualifier("repairPriceDaoImpl") EntityDao<RepairPrice> priceDao) {
        this.priceDao = priceDao;
    }

    /**
     *
     * @param id managerId
     * @return WebTable
     */
    @Override
    @Transactional(readOnly = true)
    public WebTable<WebWorkPrice> getFilledWebList(Long id) {
        List<WebWorkPrice> webList = new ArrayList<>();
        priceDao.getAll().stream()
                .filter(databasePrice->databasePrice
                .getRepairAccount().getId().equals(id))
                .forEach(databasePrice->{
                    WebWorkPrice webPrice = new WebWorkPrice();
                    webPrice.setId(databasePrice.getId());
                    webPrice.setName(databasePrice.getName());
                    webPrice.setDescription(databasePrice
                            .getPriceDescription());
                    webPrice.setSection(databasePrice
                            .getRepairSection().getName());
                    try {
                        webPrice.setCar(databasePrice.getRepairPriceCarModels().get(0).getCarMark().getName() + " "
                                + databasePrice.getRepairPriceCarModels().get(0).getName());
                    } catch (IndexOutOfBoundsException ex) {
                        LOGGER.info("no Cars in this price");
                        webPrice.setCar("Не определена");
                    }
                    webPrice.setPrice(databasePrice.getPriceValue());
                    webList.add(webPrice);

                });
        return new WebWorkPriceTable(webList);
    }

    /**
     * Unusable
     * @return null
     */
    @Override
    public WebTable<WebWorkPrice> getFilledWebList() {
        return null;
    }

}
