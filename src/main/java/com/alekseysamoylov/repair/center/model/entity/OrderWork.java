package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by alekseysamoylov on 7/3/16.
 */
@Entity
@Table(name = "ORDER_WORK")
@Access(AccessType.PROPERTY)
public class OrderWork implements EntityModel {

    private Long id;
    private RepairAccount master;
    private RepairOrder repairOrder;
    private String name;
    private BigDecimal workPrice;
    private int workValue;
    private BigDecimal workSum;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_WORK_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long workId) {
        this.id = workId;
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
    @JoinColumn(name = "ORDER_ID")
    public RepairOrder getRepairOrder() {
        return repairOrder;
    }

    public void setRepairOrder(RepairOrder repairOrder) {
        this.repairOrder = repairOrder;
    }

    @Column(name = "O_W_NAME")
    public String getName() {
        return name;
    }

    public void setName(String workName) {
        this.name = workName;
    }

    @Column(name = "O_W_PRICE")
    public BigDecimal getWorkPrice() {
        return workPrice;
    }

    public void setWorkPrice(BigDecimal workPrice) {
        this.workPrice = workPrice;
    }



    @Column(name = "O_W_VALUE")
    public int getWorkValue() {
        return workValue;
    }

    public void setWorkValue(int workValue) {
        this.workValue = workValue;
    }

    @Column(name = "O_W_SUM")
    public BigDecimal getWorkSum() {
        return workSum;
    }

    public void setWorkSum(BigDecimal workSum) {
        this.workSum = workSum;
    }


    @Override
    public String toString() {
        return "OrderWork{" +
                "workId=" + id +
                ", workName='" + name + '\'' +
                ", workPrice=" + workPrice +
                ", workValue=" + workValue +
                ", workSum=" + workSum +
                '}';
    }
}
