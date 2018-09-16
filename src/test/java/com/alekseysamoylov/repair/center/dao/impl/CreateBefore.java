package com.alekseysamoylov.repair.center.dao.impl;

import com.alekseysamoylov.repair.center.model.entity.*;

import javax.persistence.criteria.Order;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by alekseysamoylov on 7/5/16.
 * Create objects for tests
 */
class CreateBefore {
    RepairAccountRole guestRole;

    RepairAccount manager;
    RepairAccount master;
    RepairAccount client;
    AccountProperty clientProperty;
    CarMark carMark;
    CarModel carModel;

    Company company;

    AccountCar accountCar;

    OrderWork orderWork;
    OrderPart orderPart;

    RepairOrder repairOrder;

    RepairSection repairSection;

    RepairAdvice repairAdvice;
    RepairPrice repairPrice;

    PartType partType;
    PartStore partStore;

    PurchaseLog purchaseLog;

    OrderRating orderRating;


    void prepareRole() {
        guestRole = new RepairAccountRole();
        guestRole.setId(4L);
        guestRole.setName("ROLE_GUEST");
    }

    void prepareAccounts() {

        manager = new RepairAccount();
        manager.setName("Artur");
        manager.setRepairAccountPassword("pass");

        master = new RepairAccount();
        master.setName("Aleksandr");
        master.setRepairAccountPassword("pass");

        client = new RepairAccount();
        client.setName("Petya");
        client.setRepairAccountPassword("pass");

    }


    void prepareAccountProperties() {
        clientProperty = new AccountProperty();
        clientProperty.setAccount(client);
        clientProperty = new AccountProperty();
        clientProperty.setAccount(client);
        client.setAccountProperty(clientProperty);
        clientProperty.setName("Петр");
        clientProperty.setLastName("Петров");
        clientProperty.setPhoneNumber("12345678");
        clientProperty.setPhoneNumberOther("");
        clientProperty.setDiscount(10);
        clientProperty.setOther("Хороший клиент");

    }

    void prepareMark() {
        carMark = new CarMark();
        carMark.setId(12L);
        carMark.setName("Porsche");
    }

    void prepareModel() {
        carModel = new CarModel();
        carModel.setId(12L);
        carModel.setCarMark(carMark);
        carModel.setName("Cayenne");
    }

    void prepareAccountCar() {
        prepareRole();
        prepareAccounts();
        prepareMark();
        prepareModel();
        accountCar = new AccountCar();
        accountCar.setCarModel(carModel);
        accountCar.setRepairAccount(client);
        accountCar.setName("A155TT 199");
    }

    void prepareRepairOrder() {
        repairOrder = new RepairOrder();
        repairOrder.setManager(manager);
        repairOrder.setMaster(master);
        repairOrder.setClient(client);
        repairOrder.setOrderDate(new Date());
        repairOrder.setClientCar(carModel);
        repairOrder.setName("Заменить ремень ГРМ");
        repairOrder.setOrderPrepayment(BigDecimal.valueOf(600));
        repairOrder.setClientDiscount(10);
        repairOrder.setOrderSum(BigDecimal.valueOf(1300));
        repairOrder.setOrderAdvice("Заменить ступичный подшипник через 100 км");
    }


    void prepareOrderWork() {
        prepareRole();
        prepareAccounts();
        prepareModel();
        prepareRepairOrder();
        orderWork = new OrderWork();
        orderWork.setRepairOrder(repairOrder);
        orderWork.setName("Замена руля");
        orderWork.setWorkPrice(BigDecimal.valueOf(200));
        orderWork.setWorkValue(2);
        orderWork.setWorkSum(BigDecimal.valueOf(400));
    }

    void prepareOrderPart() {
        prepareRole();
        prepareAccounts();
        prepareModel();
        prepareRepairOrder();
        orderPart = new OrderPart();
        orderPart.setRepairOrder(repairOrder);
        orderPart.setName("Руль");
        orderPart.setOrderPartPrice(BigDecimal.valueOf(2700));
        orderPart.setOrderPartValue(1);
        orderPart.setOrderPartSum(BigDecimal.valueOf(2700));
    }

    void prepareRepairSection() {
        repairSection = new RepairSection();
        repairSection.setId(12L);
        repairSection.setName("Ремонт двигателя");
    }

    void prepareAdvice() {
        prepareRepairSection();
        prepareModel();
        repairAdvice = new RepairAdvice();
        repairAdvice.setRepairSection(repairSection);
        repairSection.getRepairAdvices().add(repairAdvice);
        repairAdvice.getAdviceCarModels().add(carModel);
        repairAdvice.setName("Правильная затяжка ступичного подшипника");
        repairAdvice.setAdviceText("Воспользуйтесь динамометрическим ключом");
    }

    void preparePrice() {
        prepareRepairSection();
        prepareModel();
        prepareAdvice();
        repairPrice = new RepairPrice();
        repairPrice.setRepairSection(repairSection);
        repairSection.getRepairPrices().add(repairPrice);
        repairPrice.setRepairAdvice(repairAdvice);
        repairPrice.getRepairPriceCarModels().add(carModel);
        repairPrice.setName("Замена Шаровой");
        repairPrice.setPriceDescription("Разборка подвески, замена шаровой");
        repairPrice.setPriceValue(600);

    }

    void preparePartType() {
        partType = new PartType();
        partType.setName("Элементы подвески");
    }

    void preparePartStore() {
        preparePartType();
        partStore = new PartStore();
        partStore.setPartType(partType);
        partStore.setName("Рулевой наконечник");
        partStore.setPartPrice(430);
        partStore.setPartValue(14);
    }

    void preparePurchaseLog() {
        purchaseLog = new PurchaseLog();
        purchaseLog.setLogDate(new Date());
        purchaseLog.setName("списание со склада шаровой");
    }
    
    void prepareOrderRating() {
//        orderRating.setId(repairOrder.getId());
        orderRating = new OrderRating();
        orderRating.setName("All was well..");
        orderRating.setRating(3);
        orderRating.setRepairOrder(repairOrder);
        repairOrder.setOrderRating(orderRating);
    }


}
