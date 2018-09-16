package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.OrderRating;
import com.alekseysamoylov.repair.center.model.entity.RepairOrder;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderPart;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderWork;
import com.alekseysamoylov.repair.center.site.model.element.WebPurchaseOrderTemplate;
import com.alekseysamoylov.repair.center.site.model.element.WebTextPlusAdvice;
import com.alekseysamoylov.repair.center.site.model.table.WebOrderPartTable;
import com.alekseysamoylov.repair.center.site.model.table.WebOrderWorkTable;
import com.alekseysamoylov.repair.center.site.service.fill.FillWebList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by alekseysamoylov on 7/8/16.
 */
@Service
@Transactional
public class WebPurchaseOrderStorage {

    private final EntityDao<RepairOrder> repairOrderDao;

    private final FillWebList<WebOrderWork> fillOrderWorkList;

    private final FillWebList<WebOrderPart> fillOrderPartList;

    @Autowired
    public WebPurchaseOrderStorage(@Qualifier("fillOrderWorkList") FillWebList<WebOrderWork> fillOrderWorkList, @Qualifier("orderDaoImpl") EntityDao<RepairOrder> repairOrderDao, @Qualifier("fillOrderPartList") FillWebList<WebOrderPart> fillOrderPartList) {
        this.fillOrderWorkList = fillOrderWorkList;
        this.repairOrderDao = repairOrderDao;
        this.fillOrderPartList = fillOrderPartList;
    }

    @Transactional(readOnly = true)
    public WebPurchaseOrderTemplate loadTemplate(Long id) {
        WebPurchaseOrderTemplate webPurchaseOrderTemplate = new WebPurchaseOrderTemplate();
        RepairOrder databaseOrder = repairOrderDao.get(id);
        //id
        webPurchaseOrderTemplate.setId(id);
        //managerId
        webPurchaseOrderTemplate.setManagerId(databaseOrder.getManager().getId());
        //CompanyId
        webPurchaseOrderTemplate.setCompanyId(databaseOrder.getCompany().getId());
        //order name (Заказ-наряд text)
        webPurchaseOrderTemplate.setName(databaseOrder.getName());
        webPurchaseOrderTemplate.setClient(databaseOrder
                .getClient().getAccountProperty().getLastName() + " "
                + databaseOrder.getClient().getAccountProperty().getName());
        //client phone
        webPurchaseOrderTemplate.setClientPhone(databaseOrder
                .getClient().getAccountProperty().getPhoneNumber());
        //car name & number
        webPurchaseOrderTemplate.setCarName(databaseOrder.getClientCar()
                .getCarMark().getName() + " "
                + databaseOrder.getClientCar().getName() + " Гос. Номер: "
                + databaseOrder.getClientCarNumber());
        //manager name
        webPurchaseOrderTemplate.setManagerName(databaseOrder.getManager()
                .getAccountProperty().getLastName() + " "
                + databaseOrder.getManager().getAccountProperty().getName());
        //prepayment
        webPurchaseOrderTemplate.setPrepayment(databaseOrder.getOrderPrepayment());
        //discount
        webPurchaseOrderTemplate.setDiscount(databaseOrder.getClientDiscount());
        webPurchaseOrderTemplate.setWorkDiscount(databaseOrder.isWorksDiscount());
        webPurchaseOrderTemplate.setPartDiscount(databaseOrder.isPartsDiscount());
        //advice
        webPurchaseOrderTemplate.setAdvice(databaseOrder.getOrderAdvice());
        //is complete?
        webPurchaseOrderTemplate.setComplete(databaseOrder.isOrderComplete());
        //works table
        webPurchaseOrderTemplate
                .setWebOrderWorkTable((WebOrderWorkTable) fillOrderWorkList
                .getFilledWebList(id));
        //parts table
        webPurchaseOrderTemplate
                .setWebOrderPartTable((WebOrderPartTable) fillOrderPartList
                .getFilledWebList(id));

        //discount operations
        BigDecimal sumWorks = BigDecimal.valueOf(0);
        BigDecimal sumParts = BigDecimal.valueOf(0);
        BigDecimal sumWithDiscount = BigDecimal.valueOf(0);
        for (WebOrderWork work : webPurchaseOrderTemplate
                .getWebOrderWorkTable().getWebList()) {
            sumWorks = sumWorks.add(work.getSum());
        }
        sumWithDiscount = sumWithDiscount.add(webPurchaseOrderTemplate.isWorkDiscount() ?
                (sumWorks.subtract(sumWorks
                        .multiply(BigDecimal.valueOf(webPurchaseOrderTemplate
                                .getDiscount())).multiply(BigDecimal.valueOf(0.01)))) : sumWorks);

        for (WebOrderPart part : webPurchaseOrderTemplate
                .getWebOrderPartTable().getWebList()) {
            sumParts = sumParts.add(part.getSum());
        }
        sumWithDiscount = sumWithDiscount.add(webPurchaseOrderTemplate.isPartDiscount() ?
                (sumParts.subtract(sumParts
                        .multiply(BigDecimal.valueOf(webPurchaseOrderTemplate
                                .getDiscount())).multiply(BigDecimal.valueOf(0.01)))) : sumParts);

        //Rounding two digits under zero
        //sum
        webPurchaseOrderTemplate.setWorksSum(sumWorks.setScale(2, RoundingMode.CEILING));
        webPurchaseOrderTemplate.setPartsSum(sumParts.setScale(2, RoundingMode.CEILING));
        webPurchaseOrderTemplate.setSum(sumWorks.add(sumParts.setScale(2, RoundingMode.CEILING)));
        //sum with discount
        webPurchaseOrderTemplate.setSumWithDiscount(sumWithDiscount.setScale(2, RoundingMode.CEILING));
        //get final sum
        webPurchaseOrderTemplate.setFinalSum(sumWithDiscount.subtract(webPurchaseOrderTemplate.getPrepayment()).setScale(2, RoundingMode.CEILING));
        //Rating
        OrderRating orderRating = databaseOrder.getOrderRating();
        webPurchaseOrderTemplate.setRating(orderRating == null
                || !orderRating.isComplete()
                ? "Нет оценки" : "Оценка: "
                +  orderRating.getRating()
                + " Звезд\nКомментарий: \n "
                + orderRating.getName());
        return webPurchaseOrderTemplate;
    }

    @Transactional
    public void saveTextPlusAdvice(WebTextPlusAdvice webData) {
        RepairOrder repairOrder = repairOrderDao.get(webData.getId());
        repairOrder.setName(webData.getText());
        repairOrder.setOrderAdvice(webData.getAdvice());
        repairOrder.setOrderPrepayment(webData.getPrepayment());
        repairOrder.setClientDiscount(webData.getDiscount());
        repairOrder.setWorksDiscount(webData.isDiscountWorks());
        repairOrder.setPartsDiscount(webData.isDiscountParts());
        repairOrder.setOrderSum(webData.getNoFinalSum()
                .subtract((webData.isDiscountWorks()? webData
                .getWorksSum().multiply(BigDecimal
                        .valueOf(webData.getDiscount()).multiply(BigDecimal.valueOf(0.01)))
                        :
                        BigDecimal.valueOf(0)))
                .subtract(webData.isDiscountParts()? webData
                        .getPartsSum().multiply(BigDecimal
                                .valueOf(webData.getDiscount()).multiply(BigDecimal.valueOf(0.01)))
                        :
                        BigDecimal.valueOf(0)));
        repairOrder.setOrderComplete(webData.isComplete());
        repairOrderDao.edit(repairOrder);
    }



}
