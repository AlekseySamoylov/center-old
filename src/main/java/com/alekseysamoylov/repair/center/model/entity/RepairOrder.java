package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/3/16.
 */
@Entity
@Table(name = "REPAIR_ORDER")
@Access(AccessType.PROPERTY)
public class RepairOrder implements EntityModel, Comparable<RepairOrder> {

    private Long id;
    private Company company;
    private Date orderDate;
    private RepairAccount manager;
    private RepairAccount master;
    private RepairAccount client;
    private CarModel clientCar;
    private String clientCarNumber;
    private String name;
    private List<OrderWork> orderWorks = new ArrayList<>();
    private List<OrderPart> orderParts = new ArrayList<>();
    private BigDecimal orderPrepayment;
    private int clientDiscount;
    private boolean worksDiscount;
    private boolean partsDiscount;
    private BigDecimal worksSum;
    private BigDecimal partsSum;
    private BigDecimal orderSum;
    private boolean orderComplete;
    private String orderAdvice;
    private OrderRating orderRating;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long orderId) {
        this.id = orderId;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "COMPANY_ID")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_DATE")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "MANAGER_ACCOUNT_ID")
    public RepairAccount getManager() {
        return manager;
    }

    public void setManager(RepairAccount manager) {
        this.manager = manager;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "MASTER_ACCOUNT_ID")
    public RepairAccount getMaster() {
        return master;
    }

    public void setMaster(RepairAccount master) {
        this.master = master;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "CLIENT_ACCOUNT_ID")
    public RepairAccount getClient() {
        return client;
    }

    public void setClient(RepairAccount client) {
        this.client = client;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "CLIENT_CAR_MODEL")
    public CarModel getClientCar() {
        return clientCar;
    }

    public void setClientCar(CarModel clientCar) {
        this.clientCar = clientCar;
    }

    @Column(name = "CLIENT_CAR_NUMBER")
    public String getClientCarNumber() {
        return clientCarNumber;
    }

    public void setClientCarNumber(String clientCarNumber) {
        this.clientCarNumber = clientCarNumber;
    }

    @Column(name = "ORDER_DESCRIPTION", columnDefinition="TEXT")
    public String getName() {
        return name;
    }

    public void setName(String orderDescription) {
        this.name = orderDescription;
    }

    @OneToMany(mappedBy = "repairOrder", cascade = CascadeType.ALL)
    public List<OrderWork> getOrderWorks() {
        return orderWorks;
    }

    public void setOrderWorks(List<OrderWork> orderWorks) {
        this.orderWorks = orderWorks;
    }

    @OneToMany(mappedBy = "repairOrder", cascade = CascadeType.ALL)
    public List<OrderPart> getOrderParts() {
        return orderParts;
    }

    public void setOrderParts(List<OrderPart> orderParts) {
        this.orderParts = orderParts;
    }

    @Column(name = "ORDER_PREPAYMENT")
    public BigDecimal getOrderPrepayment() {
        return orderPrepayment;
    }

    public void setOrderPrepayment(BigDecimal orderPrepayment) {
        this.orderPrepayment = orderPrepayment;
    }

    @Column(name = "WORKS_SUM")
    public BigDecimal getWorksSum() {
        return worksSum;
    }

    public void setWorksSum(BigDecimal worksSum) {
        this.worksSum = worksSum;
    }

    @Column(name = "PARTS_SUM")
    public BigDecimal getPartsSum() {
        return partsSum;
    }

    public void setPartsSum(BigDecimal partsSum) {
        this.partsSum = partsSum;
    }

    @Column(name = "ORDER_SUM")
    public BigDecimal getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(BigDecimal orderSum) {
        this.orderSum = orderSum;
    }

//        @Column(name = "ORDER_PREPAYMENT")
//    public double getOrderPrepayment() {
//        return orderPrepayment;
//    }
//
//    public void setOrderPrepayment(double orderPrepayment) {
//        this.orderPrepayment = orderPrepayment;
//    }

    @Column(name = "DISCOUNT")
    public int getClientDiscount() {
        return clientDiscount;
    }

    public void setClientDiscount(int clientDiscount) {
        this.clientDiscount = clientDiscount;
    }

    @Column(name = "WORKS_DISCOUNT")
    public boolean isWorksDiscount() {
        return worksDiscount;
    }

    public void setWorksDiscount(boolean worksDiscount) {
        this.worksDiscount = worksDiscount;
    }

    @Column(name = "PARTS_DISCOUNT")
    public boolean isPartsDiscount() {
        return partsDiscount;
    }

    public void setPartsDiscount(boolean partsDiscount) {
        this.partsDiscount = partsDiscount;
    }

    @Column(name = "ORDER_COMPLETE")
    public boolean isOrderComplete() {
        return orderComplete;
    }

    public void setOrderComplete(boolean orderComplete) {
        this.orderComplete = orderComplete;
    }

    @Column(name = "ORDER_ADVICE")
    public String getOrderAdvice() {
        return orderAdvice;
    }

    public void setOrderAdvice(String orderAdvice) {
        this.orderAdvice = orderAdvice;
    }

    @OneToOne(mappedBy = "repairOrder", cascade = CascadeType.ALL)
    public OrderRating getOrderRating() {
        return orderRating;
    }

    public void setOrderRating(OrderRating orderRating) {
        this.orderRating = orderRating;
    }

    @Override
    public int compareTo(RepairOrder o) {
        return orderDate.compareTo(o.getOrderDate());
    }

    @Override
    public String toString() {
        return "RepairOrder{" +
                "orderId=" + id +
                "orderDate=" + orderDate +
                ", manager=" + manager.getName() +
                ", master=" + master.getName() +
                ", client=" + client.getName() +
                ", clientCar=" + clientCar.getCarMark().getName() +
                clientCar.getName() +
                ", orderDescription='" + name + '\'' +
                ", orderPrepayment=" + orderPrepayment +
                ", clientDiscount=" + clientDiscount + "%" +
                ", orderSum=" + orderSum +
                ", orderAdvice='" + orderAdvice + '\'' +
                '}';
    }


}
