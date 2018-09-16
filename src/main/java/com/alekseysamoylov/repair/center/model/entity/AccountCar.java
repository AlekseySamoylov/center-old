package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * Created by alekseysamoylov on 7/6/16.
 */
@Entity
@Table(name = "ACCOUNT_CAR")
@Access(AccessType.PROPERTY)
public class AccountCar implements EntityModel{
    private Long id;
    private String name;
    private RepairAccount repairAccount;
    private CarModel carModel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_CAR_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ACCOUNT_CAR_NUMBER")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "ACCOUNT_ID")
    public RepairAccount getRepairAccount() {
        return repairAccount;
    }

    public void setRepairAccount(RepairAccount repairAccount) {
        this.repairAccount = repairAccount;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "CAR_MODEL_ID")
    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        return "Автомобиль '" + carModel.getCarMark().getName() + " " + carModel.getName() + " " + name + '\'' +
                '}';
    }
}
