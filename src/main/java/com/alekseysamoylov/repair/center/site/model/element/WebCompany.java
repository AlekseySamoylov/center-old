package com.alekseysamoylov.repair.center.site.model.element;

/**
 * Created by alekseysamoylov on 7/15/16.
 */
public class WebCompany implements Comparable<WebCompany>{
    private Long id;
    private String name;
    private String country;
    private String city;
    private String district;
    private String street;
    private String house;
    private String phone;
    private String site;
    private String email;
    private String other;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "WebCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", site='" + site + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", phone='" + phone + '\'' +
                ", other='" + other + '\'' +
                '}';
    }

    @Override
    public int compareTo(WebCompany o) {
        return name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof WebCompany) && this.id.equals(((WebCompany) obj).getId());
    }
}
