package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by alekseysamoylov on 7/3/16.
 */
@Entity
@Table(name = "PURCHASE_LOG")
@Access(AccessType.PROPERTY)
public class PurchaseLog implements EntityModel, Comparable<PurchaseLog> {

    private Long id;
    private Date logDate;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASE_LOG_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long logId) {
        this.id = logId;
    }

    @Column(name = "PURCHASE_LOG_DATE")
    @Temporal(TemporalType.DATE)
    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    @Column(name = "PURCHASE_LOG_TEXT")
    public String getName() {
        return name;
    }

    public void setName(String logText) {
        this.name = logText;
    }



    @Override
    public String toString() {
        return "PurchaseLog{" +
                "logId=" + id +
                ", logText='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(PurchaseLog o) {
        return o.getLogDate().compareTo(this.logDate);
    }
}
