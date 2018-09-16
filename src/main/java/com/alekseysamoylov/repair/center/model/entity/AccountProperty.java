package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Created by alekseysamoylov on 7/4/16.
 */
@Entity
@Table(name = "CAR_REPAIR_ACCOUNT_PROPERTY")
@Access(AccessType.PROPERTY)
public class AccountProperty implements EntityModel {

    private Long id;
    private RepairAccount account;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String phoneNumberOther;
    private String other;
    private int discount;

    @Id
    @GeneratedValue(generator="myGenerator")
    @GenericGenerator(name="myGenerator", strategy="foreign",
            parameters=@Parameter(value="account", name = "property"))
    @Column(name = "ACCOUNT_PROPERTY_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long accountId) {
        this.id = accountId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_PROPERTY_ID")
    public RepairAccount getAccount() {
        return account;
    }

    public void setAccount(RepairAccount account) {
        this.account = account;
    }

    @Column(name = "FIRST_NAME")
    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "PHONE_NUMBER_OTHER")
    public String getPhoneNumberOther() {
        return phoneNumberOther;
    }

    public void setPhoneNumberOther(String phoneNumberOther) {
        this.phoneNumberOther = phoneNumberOther;
    }

    @Column(name = "OTHER")
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Column(name = "DISCOUNT")
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "AccountProperty{" +
                "accountId=" + id +
                ", firstName='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneNumberOther='" + phoneNumberOther + '\'' +
                ", other='" + other + '\'' +
                ", discount=" + discount +
                '}';
    }


}
