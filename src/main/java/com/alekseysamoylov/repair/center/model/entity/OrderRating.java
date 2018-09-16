package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by alekseysamoylov on 7/29/16.
 */
@Entity
@Table(name = "ORDER_RATING")
@Access(AccessType.PROPERTY)
public class OrderRating implements EntityModel {

    private Long id;
    private int rating;
    private String name;
    private RepairOrder repairOrder;
    private boolean complete;

    @Override
    @Id
    @GeneratedValue(generator="myGenerator2")
    @GenericGenerator(name="myGenerator2", strategy="foreign",
            parameters=@Parameter(value="repairOrder", name = "property"))
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "RATING")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    @Column(name = "COMMENT")
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "COMPLETE", columnDefinition = "boolean default FALSE")
    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @OneToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "ID")
    public RepairOrder getRepairOrder() {
        return repairOrder;
    }

    public void setRepairOrder(RepairOrder repairOrder) {
        this.repairOrder = repairOrder;
    }

    @Override
    public String toString() {
        return "OrderRating{" +
                "id=" + id +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                ", complete=" + complete +
                '}';
    }
}
