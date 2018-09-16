package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.Company;
import com.alekseysamoylov.repair.center.model.entity.RepairOrder;
import com.alekseysamoylov.repair.center.site.model.element.WebUserInformation;
import com.alekseysamoylov.repair.center.site.model.json.DiagramModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alekseysamoylov on 8/7/16.
 */
@Service
@Transactional
public class DiagramModelStoreImpl implements DiagramModelStore {

    private EntityDao<Company> companyEntityDao;

    @Autowired
    public DiagramModelStoreImpl(@Qualifier("companyDaoImpl") EntityDao<Company> companyEntityDao) {
        this.companyEntityDao = companyEntityDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiagramModel> getDiagramModels() {
        String tempDateOut = "";
        List<DiagramModel> diagramModelList = new ArrayList<>();
        DiagramModel diagramModel = new DiagramModel();
        Company company = companyEntityDao.get(((WebUserInformation) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCompanyId());
        List<RepairOrder> orderList = company.getOrders();
        Collections.sort(orderList);
        Iterator<RepairOrder> iterator = orderList.iterator();
        while (iterator.hasNext()) {
            RepairOrder repairOrder = iterator.next();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd yyyy");
            String tempDateIn = simpleDateFormat.format(repairOrder.getOrderDate());
            if (tempDateOut.equals("")) {
                tempDateOut = tempDateIn;
                diagramModel = new DiagramModel();
                diagramModel.simpleDate = tempDateOut;
                diagramModel.daySum = repairOrder.getOrderSum();
            }  else if (tempDateOut.equals(tempDateIn)){
                diagramModel.daySum.add(repairOrder.getOrderSum());
            } else {
                diagramModelList.add(diagramModel);
                tempDateOut = tempDateIn;
                diagramModel = new DiagramModel();
                diagramModel.simpleDate = tempDateOut;
                diagramModel.daySum = repairOrder.getOrderSum();
            }

            if (!iterator.hasNext()) diagramModelList.add(diagramModel);
        }
//        for (RepairOrder repairOrder : company.getOrders()){
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
//            String tempDateIn = simpleDateFormat.format(repairOrder.getOrderDate());
//            if (tempDateOut.equals("")) {
//                tempDateOut = tempDateIn;
//                diagramModel = new DiagramModel();
//                diagramModel.simpleDate = tempDateOut;
//                diagramModel.daySum = repairOrder.getOrderSum();
//            }  else if (tempDateOut.equals(tempDateIn)){
//                diagramModel.daySum.add(repairOrder.getOrderSum());
//            } else {
//                diagramModelList.add(diagramModel);
//                tempDateOut = tempDateIn;
//                diagramModel = new DiagramModel();
//                diagramModel.simpleDate = tempDateOut;
//                diagramModel.daySum = repairOrder.getOrderSum();
//            }
//        }
        return diagramModelList;
    }
}
