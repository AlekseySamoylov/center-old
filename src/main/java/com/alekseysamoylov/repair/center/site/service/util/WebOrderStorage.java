package com.alekseysamoylov.repair.center.site.service.util;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.*;
import com.alekseysamoylov.repair.center.model.exceptions.WrongUserAccessException;
import com.alekseysamoylov.repair.center.service.PurchaseLogService;
import com.alekseysamoylov.repair.center.site.model.element.WebCompany;
import com.alekseysamoylov.repair.center.site.model.element.WebOrder;
import com.alekseysamoylov.repair.center.site.model.element.WebOrderTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/8/16.
 */
@Service
@Transactional
public class WebOrderStorage {
    private final Logger LOGGER = Logger.getLogger(WebOrderStorage.class);

    private Date orderDate;
    private Long companyId;
    private Long managerId;
    private Long clientId;
    private Long carModelId;
    private String clientCarNumber;
    private String mainOrderText;

    private final EntityDao<CarModel> carModelDao;

    private final EntityDao<AccountCar> accountCarDao;

    private final EntityDao<RepairOrder> repairOrderDao;

    private final EntityDao<RepairAccount> repairAccountDao;

    private final EntityDao<Company> companyDao;

    private final ClientSaving clientSaving;

    private final FillWebObject<WebCompany, Long> webCompanyInformationFill;

    private final PurchaseLogService purchaseLogService;


    @Autowired
    public WebOrderStorage(@Qualifier("accountCarDaoImpl") EntityDao<AccountCar> accountCarDao, @Qualifier("orderDaoImpl") EntityDao<RepairOrder> repairOrderDao, @Qualifier("carModelDaoImpl") EntityDao<CarModel> carModelDao, ClientSaving clientSaving, @Qualifier("webCompanyInformationFill") FillWebObject<WebCompany, Long> webCompanyInformationFill, @Qualifier("companyDaoImpl") EntityDao<Company> companyDao, @Qualifier("repairAccountDaoImpl") EntityDao<RepairAccount> repairAccountDao, PurchaseLogService purchaseLogService) {
        this.accountCarDao = accountCarDao;
        this.repairOrderDao = repairOrderDao;
        this.carModelDao = carModelDao;
        this.clientSaving = clientSaving;
        this.webCompanyInformationFill = webCompanyInformationFill;
        this.companyDao = companyDao;
        this.repairAccountDao = repairAccountDao;
        this.purchaseLogService = purchaseLogService;
    }

    @Transactional
    public void deleteWebOrder(Long id) {
        repairOrderDao.delete(id);
    }

    /**
     * Use it!
     * @param orderId orderId
     * @param companyId companyId
     */
    @Transactional
    public void deleteWebOrder(Long orderId, Long companyId) {
        RepairOrder repairOrder = repairOrderDao.get(orderId);
        String logText = repairOrder.getCompany().getName();
        if (logText.length() > 60) logText = logText.substring(0, 60) + "...";
        purchaseLogService.log("Company " + logText + " has deleted order N " + repairOrder.getId()
                + repairOrder.getName() + " " + repairOrder.getOrderSum() + " p.");
        if (repairOrderDao.get(orderId).getCompany().getId().equals(companyId))
        repairOrderDao.delete(orderId);
        else throw new WrongUserAccessException();
    }

    /**
     * prepare fields to save order
     *
     * @param webOrderTemplate
     */
    @Transactional
    public Long saveNewWebOrder(WebOrderTemplate webOrderTemplate) {
        orderDate = new Date();
        managerId = webOrderTemplate.getManagerId();
        companyId = webOrderTemplate.getCompanyId();

        //get client id
        if (webOrderTemplate.isClientInBase()) {
            clientId = webOrderTemplate.getClientId();
        } else {
            try {
                clientId = clientSaving.saveClientFromWebToDatabase(
                        webOrderTemplate.getFirstName(),
                        webOrderTemplate.getLastName(),
                        webOrderTemplate.getPhone(),
                        webOrderTemplate.getCompanyId());
            } catch (IOException e) {
                LOGGER.warn("SaveClient IOException config.properties" + e);
            }
        }
        //Get car parameters(model & gos number)
        if (webOrderTemplate.isClientCarInList()) {
            AccountCar accountCar = accountCarDao
                    .get(webOrderTemplate.getClientCarId());
            carModelId = accountCar.getCarModel().getId();
            clientCarNumber = accountCar.getName();
        } else {
            carModelId = webOrderTemplate.getCarModelId();
            clientCarNumber = webOrderTemplate.getClientCarNumber();
            AccountCar accountCar = new AccountCar();
            accountCar.setRepairAccount(repairAccountDao.get(clientId));
            accountCar.setCarModel(carModelDao.get(carModelId));
            accountCar.setName(webOrderTemplate.getClientCarNumber());
            accountCarDao.add(accountCar);

        }

        mainOrderText = webOrderTemplate.getMainOrderText();
        return saveOrder();
    }


    @Transactional
    private Long saveOrder() {
        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setCompany(companyDao.get(companyId));
        repairOrder.setOrderDate(orderDate);
        repairOrder.setManager(repairAccountDao.get(managerId));
        //Master ID !!!!
        repairOrder.setMaster(repairAccountDao.get(1L));
        repairOrder.setClient(repairAccountDao.get(clientId));
        repairOrder.setClientCar(carModelDao.get(carModelId));
        repairOrder.setClientCarNumber(clientCarNumber);
        repairOrder.setName(mainOrderText);
        repairOrder.setOrderSum(BigDecimal.valueOf(0));
        repairOrder.setPartsSum(BigDecimal.valueOf(0));
        repairOrder.setWorksSum(BigDecimal.valueOf(0));
        repairOrder.setOrderPrepayment(BigDecimal.valueOf(0));

        return repairOrderDao.add(repairOrder);
    }

    @Transactional(readOnly = true)
    public List<WebOrder> getOrdersByClientId(Long clientId) {
        List<WebOrder> webOrders = new ArrayList<>();
        repairOrderDao.getAll().stream()
                .filter(dbOrder -> dbOrder.getClient().getId()
                        .equals(clientId)).forEach(dbOrder -> {
            WebOrder webOrder = new WebOrder();
            webOrder.setId(dbOrder.getId());
            webOrder.setWebCompany(webCompanyInformationFill
                    .getFilledWebObject(dbOrder
                            .getCompany().getId()));
            webOrder.setName(dbOrder.getName());
            webOrder.setComplete(dbOrder.isOrderComplete());
            webOrder.setOrderDate(dbOrder.getOrderDate());
            NumberFormat formatter = new DecimalFormat("#0.00");
            webOrder.setSum(dbOrder.getOrderSum() + " р.");
            webOrders.add(webOrder);
        });
        return webOrders;
    }

    @Transactional(readOnly = true)
    public List<WebOrder> getAllWebOrders() {
        List<WebOrder> webOrders = new ArrayList<>();
        for (RepairOrder databaseOrder : repairOrderDao.getAll()) {
            WebOrder webOrder = new WebOrder();
            //orderId
            webOrder.setId(databaseOrder.getId());
            //webCompany
            webOrder.setWebCompany(webCompanyInformationFill.getFilledWebObject(databaseOrder.getCompany().getId()));
            //date
            webOrder.setOrderDate(databaseOrder.getOrderDate());
            //Create string Car Mark + Model + GosNumber
            webOrder.setName(String.valueOf(new StringBuilder(databaseOrder
                    .getClientCar().getCarMark().getName())
                    .append(" ").append(databaseOrder.getClientCar().getName())
                    .append(" ").append(databaseOrder.getClientCarNumber())));

            //description zakaz-naryad
            String descTemp = databaseOrder.getName();
            if (descTemp.length() > 52) descTemp = descTemp.substring(0, 50) + "...";
            webOrder.setDescription(descTemp);
            //sum
            NumberFormat formatter = new DecimalFormat("#0.00");
            webOrder.setSum(databaseOrder.getOrderSum() + " р.");
            //complition
            webOrder.setComplete(databaseOrder.isOrderComplete());
            //Rating
            OrderRating orderRating = databaseOrder.getOrderRating();
            webOrder.setRating(orderRating == null ? 0 : orderRating.getRating());
            webOrders.add(webOrder);
        }
        return webOrders;
    }

}
