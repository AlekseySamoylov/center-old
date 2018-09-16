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
@Table(name = "CAR_MODEL")
@Access(AccessType.PROPERTY)
public class CarModel implements EntityModel {

    private Long id;
    private CarMark carMark;
    private String name;
    private List<RepairSection> repairSections = new ArrayList<>();
    private List<RepairPrice> repairPrices = new ArrayList<>();
    private List<RepairAdvice> repairAdvices = new ArrayList<>();
    private List<AccountCar> repairAccountCars = new ArrayList<>();

    @Id
    @Column(name = "CAR_MODEL_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long modelId) {
        this.id = modelId;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "CAR_MARK_ID")
    public CarMark getCarMark() {
        return carMark;
    }

    public void setCarMark(CarMark carMark) {
        this.carMark = carMark;
    }

    @Column(name = "CAR_MODEL_NAME")
    public String getName() {
        return name;
    }

    public void setName(String modelName) {
        this.name = modelName;
    }

    @ManyToMany(mappedBy = "repairSectionCarModels")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<RepairSection> getRepairSections() {
        return repairSections;
    }

    public void setRepairSections(List<RepairSection> repairSections) {
        this.repairSections = repairSections;
    }

    @ManyToMany(mappedBy = "repairPriceCarModels")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<RepairPrice> getRepairPrices() {
        return repairPrices;
    }

    public void setRepairPrices(List<RepairPrice> repairPrices) {
        this.repairPrices = repairPrices;
    }

    @ManyToMany(mappedBy = "adviceCarModels")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<RepairAdvice> getRepairAdvices() {
        return repairAdvices;
    }

    public void setRepairAdvices(List<RepairAdvice> repairAdvices) {
        this.repairAdvices = repairAdvices;
    }

    @OneToMany(mappedBy = "carModel")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<AccountCar> getRepairAccountCars() {
        return repairAccountCars;
    }

    public void setRepairAccountCars(List<AccountCar> repairAccountCars) {
        this.repairAccountCars = repairAccountCars;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "modelId=" + id +
                ", carMark=" + carMark.getName() +
                ", modelName='" + name + '\'' +
                ", repairSections=" + repairSections +
                '}';
    }
}
