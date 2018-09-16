package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;

import javax.persistence.*;

/**
 * Created by alekseysamoylov on 7/3/16.
 */
@Entity
@Table(name = "PART_STORE")
@Access(AccessType.PROPERTY)
public class PartStore implements EntityModel {

    private Long id;
    private PartType partType;
    private String name;
    private int partValue;
    private double partPrice;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PART_STORE_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long partStoreId) {
        this.id = partStoreId;
    }

    @ManyToOne
    @JoinColumn(name = "PART_TYPE_ID")
    public PartType getPartType() {
        return partType;
    }

    public void setPartType(PartType partType) {
        this.partType = partType;
    }

    @Column(name = "PART_STORE_NAME")
    public String getName() {
        return name;
    }

    public void setName(String partName) {
        this.name = partName;
    }

    @Column(name = "PART_STORE_VALUE")
    public int getPartValue() {
        return partValue;
    }

    public void setPartValue(int partValue) {
        this.partValue = partValue;
    }

    @Column(name = "PART_STORE_PRICE")
    public double getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }

    @Override
    public String toString() {
        return "PartStore{" +
                "partStoreId=" + id +
                ", partType=" + partType.getName() +
                ", partName='" + name + '\'' +
                ", partValue=" + partValue +
                ", partPrice=" + partPrice +
                '}';
    }
}
