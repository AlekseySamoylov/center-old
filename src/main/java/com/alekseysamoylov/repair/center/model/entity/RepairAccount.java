package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 6/27/16.
 * Account
 */
@Entity
//@Indexed
@Table(name = "CAR_REPAIR_ACCOUNT")
@Access(AccessType.PROPERTY)
public class RepairAccount implements EntityModel {
    private final Logger LOGGER = Logger.getLogger(RepairAccount.class);
    private Long id;
    private RepairAccountRole repairAccountRole;
    private String name;
    private String repairAccountPassword;

    private AccountProperty accountProperty;
    private List<Company> companies = new ArrayList<>();
    private List<AccountCar> accountCars = new ArrayList<>();
    private List<RepairOrder> managerRepairOrders = new ArrayList<>();
    private List<RepairOrder> masterRepairOrders = new ArrayList<>();
    private List<RepairOrder> clientRepairOrders = new ArrayList<>();
    private List<OrderWork> masterOrderWorks = new ArrayList<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_REPAIR_ACCOUNT_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long repairAccountId) {
        this.id = repairAccountId;
    }

    @ManyToOne
    @JoinColumn(name = "CAR_REPAIR_ACCOUNT_ROLE_ID")
    public RepairAccountRole getRepairAccountRole() {
        return repairAccountRole;
    }

    public void setRepairAccountRole(RepairAccountRole repairAccountRole) {
        this.repairAccountRole = repairAccountRole;
    }

//    @Field(index=Index.YES, analyze= Analyze.YES, store= Store.NO)
    @Column(name = "CAR_REPAIR_ACCOUNT_NAME")
    public String getName() {
        return name;
    }

    public void setName(String repairAccountName) {
        this.name = repairAccountName;
    }

    @Column(name = "CAR_REPAIR_ACCOUNT_PASSWORD")
    public String getRepairAccountPassword() {
        return repairAccountPassword;
    }

    public void setRepairAccountPassword(String repairAccountPassword) {
        this.repairAccountPassword = repairAccountPassword;
    }

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    public AccountProperty getAccountProperty() {
        return accountProperty;
    }

    public void setAccountProperty(AccountProperty accountProperty) {
        this.accountProperty = accountProperty;
    }

    @ManyToMany(mappedBy = "members")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @OneToMany(mappedBy = "repairAccount", cascade = CascadeType.ALL)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<AccountCar> getAccountCars() {
        return accountCars;
    }

    public void setAccountCars(List<AccountCar> accountCars) {
        this.accountCars = accountCars;
    }

    @OneToMany(mappedBy = "manager")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<RepairOrder> getManagerRepairOrders() {
        return managerRepairOrders;
    }

    public void setManagerRepairOrders(List<RepairOrder> managerRepairOrders) {
        this.managerRepairOrders = managerRepairOrders;
    }

    @OneToMany(mappedBy = "master")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<RepairOrder> getMasterRepairOrders() {
        return masterRepairOrders;
    }

    public void setMasterRepairOrders(List<RepairOrder> masterRepairOrders) {
        this.masterRepairOrders = masterRepairOrders;
    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    public List<RepairOrder> getClientRepairOrders() {
        return clientRepairOrders;
    }

    public void setClientRepairOrders(List<RepairOrder> clientRepairOrders) {
        this.clientRepairOrders = clientRepairOrders;
    }

    @OneToMany(mappedBy = "master")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<OrderWork> getMasterOrderWorks() {
        return masterOrderWorks;
    }

    public void setMasterOrderWorks(List<OrderWork> masterOrderWorks) {
        this.masterOrderWorks = masterOrderWorks;
    }

    @Override
    public String toString() {
        return "RepairAccount{" +
                "repairAccountId=" + id +
                "repairAccountRole=" + repairAccountRole.getName() +
                ", repairAccountName='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepairAccount that = (RepairAccount) o;

        if (!id.equals(that.id)) return false;
        if (!repairAccountRole.equals(that.repairAccountRole)) return false;
        if (!name.equals(that.name)) return false;
        return repairAccountPassword.equals(that.repairAccountPassword);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + repairAccountRole.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + repairAccountPassword.hashCode();
        return result;
    }
}
