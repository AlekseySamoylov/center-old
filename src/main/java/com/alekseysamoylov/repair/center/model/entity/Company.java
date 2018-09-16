package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
@Entity
@Table(name = "REPAIR_COMPANY")
@Access(AccessType.PROPERTY)
public class Company implements EntityModel {

    private Long id;
    private String name;
    private List<RepairAccount> members = new ArrayList<>();
    private List<RepairOrder> orders = new ArrayList<>();
    private String country;
    private String city;
    private String district;
    private String street;
    private String house;
    private String phone;
    private String email;
    private String site;
    private String other;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID")
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    @Column(name = "COMPANY_NAME")
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinTable(
            name = "COMPANY_ACCOUNT",
            joinColumns = @JoinColumn(name = "COMPANY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID"))
    public List<RepairAccount> getMembers() {
        return members;
    }

    public void setMembers(List<RepairAccount> members) {
        this.members = members;
    }

    @OneToMany(mappedBy = "company")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<RepairOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<RepairOrder> orders) {
        this.orders = orders;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "DISTRICT")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "HOUSE")
    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "SITE")
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Column(name = "OTHER")
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(id);
    }

    @Override
    public boolean equals(Object obj) {
        return id.equals(((Company)obj).getId());
    }


    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", site='" + site + '\'' +
                ", other='" + other + '\'' +
                '}';
    }

}
