package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/3/16.
 */
@Entity
@Table(name = "CAR_REPAIR_PRICE")
@Access(AccessType.PROPERTY)
public class RepairPrice implements EntityModel {

    private Long id;
    private RepairSection repairSection;
    private List<CarModel> repairPriceCarModels = new ArrayList<>();
    private String name;
    private String priceDescription;
    private double priceValue;
    private RepairAdvice repairAdvice;

    private RepairAccount repairAccount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_REPAIR_PRICE_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long priceId) {
        this.id = priceId;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "CAR_REPAIR_SECTION_ID")
    public RepairSection getRepairSection() {
        return repairSection;
    }

    public void setRepairSection(RepairSection repairSection) {
        this.repairSection = repairSection;
    }

    @ManyToMany
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinTable(
            name = "CAR_MODEL_REPAIR_PRICE",
            joinColumns = @JoinColumn(name = "CAR_REPAIR_PRICE_ID"),
            inverseJoinColumns = @JoinColumn(name = "CAR_MODEL_ID"))
    public List<CarModel> getRepairPriceCarModels() {
        return repairPriceCarModels;
    }

    public void setRepairPriceCarModels(List<CarModel> repairPriceCarModels) {
        this.repairPriceCarModels = repairPriceCarModels;
    }

    @Column(name = "CAR_REPAIR_PRICE_NAME")
    public String getName() {
        return name;
    }

    public void setName(String priceName) {
        this.name = priceName;
    }

    @Column(name = "CAR_REPAIR_PRICE_DESCRIPTION")
    public String getPriceDescription() {
        return priceDescription;
    }

    public void setPriceDescription(String priceDescription) {
        this.priceDescription = priceDescription;
    }

    @Column(name = "CAR_REPAIR_PRICE_PRICE")
    public double getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(double priceValue) {
        this.priceValue = priceValue;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "CAR_REPAIR_ADVICE_ID")
    public RepairAdvice getRepairAdvice() {
        return repairAdvice;
    }

    public void setRepairAdvice(RepairAdvice repairAdvice) {
        this.repairAdvice = repairAdvice;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "CAR_REPAIR_ACCOUNT_ID")
    public RepairAccount getRepairAccount() {
        return repairAccount;
    }

    public void setRepairAccount(RepairAccount repairAccount) {
        this.repairAccount = repairAccount;
    }

    @Override
    public String toString() {
        return "RepairPrice{" +
                "priceId=" + id +
                ", repairSection=" + repairSection.getName() +
                ", priceName='" + name + '\'' +
                ", priceDescription='" + priceDescription + '\'' +
                ", priceValue=" + priceValue +
                '}';
    }
}
