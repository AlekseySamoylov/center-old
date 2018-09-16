package com.alekseysamoylov.repair.center.site.model.element;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/15/16.
 */
public class WebUserInformation implements UserDetails {
    private Long id;
    private Long companyId;
    private String role;
    private String name;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String otherPhone;
    private List<WebCompany> companies = new ArrayList<>();
    private List<WebClientCar> cars = new ArrayList<>();
    private List<WebOrder> orders = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    public List<WebCompany> getCompanies() {
        return companies;
    }

    public void setCompanies(List<WebCompany> companies) {
        this.companies = companies;
    }

    public List<WebClientCar> getCars() {
        return cars;
    }

    public void setCars(List<WebClientCar> cars) {
        this.cars = cars;
    }

    public List<WebOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<WebOrder> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "WebUserInformation{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", otherPhone='" + otherPhone + '\'' +
                ", companies=" + companies +
                ", cars=" + cars +
                ", orders=" + orders +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(this.role);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
