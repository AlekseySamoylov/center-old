package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by alekseysamoylov on 7/3/16.
 */
@Entity
@Table(name = "ORDER_PART")
@Access(AccessType.PROPERTY)
public class OrderPart implements EntityModel {

    private Long id;
    private RepairOrder repairOrder;
    private String name;
    private BigDecimal orderPartPrice;
    private int orderPartValue;
    private BigDecimal orderPartSum;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_PART_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long orderPartId) {
        this.id = orderPartId;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "ORDER_ID")
    public RepairOrder getRepairOrder() {
        return repairOrder;
    }

    public void setRepairOrder(RepairOrder repairOrder) {
        this.repairOrder = repairOrder;
    }

    @Column(name = "O_P_NAME")
    public String getName() {
        return name;
    }

    public void setName(String orderPartName) {
        this.name = orderPartName;
    }

    @Column(name = "O_P_PRICE")
    public BigDecimal getOrderPartPrice() {
        return orderPartPrice;
    }

    public void setOrderPartPrice(BigDecimal orderPartPrice) {
        this.orderPartPrice = orderPartPrice;
    }

    @Column(name = "O_P_VALUE")
    public int getOrderPartValue() {
        return orderPartValue;
    }

    public void setOrderPartValue(int orderPartValue) {
        this.orderPartValue = orderPartValue;
    }

    @Column(name = "O_P_SUM")
    public BigDecimal getOrderPartSum() {
        return orderPartSum;
    }

    public void setOrderPartSum(BigDecimal orderPartSum) {
        this.orderPartSum = orderPartSum;
    }



    @Override
    public String toString() {
        return "OrderPart{" +
                "orderPartId=" + id +
                ", orderPartName='" + name + '\'' +
                ", orderPartPrice=" + orderPartPrice +
                ", orderPartValue=" + orderPartValue +
                ", orderPartSum=" + orderPartSum +
                '}';
    }
}
