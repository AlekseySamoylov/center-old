package com.alekseysamoylov.repair.center.site.model.element;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
public class WebOrderTemplate {
    // Fix it!!!!
    private Long managerId;
    private Long companyId;
//    private Date date;
    private boolean clientInBase = false;
    //is client in base? - no
    private String firstName;
    private String lastName;
    private String phone;
    //is client in base? - yes
    private Long clientId;
    private boolean clientCarInList = false;
    //is clientCar in list? - no
    private Long carModelId;
    //is carModel in list? - no
    private String carModelName;
    //is carModel in list? - yes
    private Long clientCarId = -1L;
    private String clientCarNumber;
    private String mainOrderText;

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }


    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public boolean isClientInBase() {
        return clientInBase;
    }

    public void setClientInBase(boolean clientInBase) {
        this.clientInBase = clientInBase;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public boolean isClientCarInList() {
        return clientCarInList;
    }

    public void setClientCarInList(boolean clientCarInList) {
        this.clientCarInList = clientCarInList;
    }

    public Long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Long carModelId) {
        this.carModelId = carModelId;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }

    public Long getClientCarId() {
        return clientCarId;
    }

    public void setClientCarId(Long clientCarId) {
        this.clientCarId = clientCarId;
    }

    public String getClientCarNumber() {
        return clientCarNumber;
    }

    public void setClientCarNumber(String clientCarNumber) {
        this.clientCarNumber = clientCarNumber;
    }

    public String getMainOrderText() {
        return mainOrderText;
    }

    public void setMainOrderText(String mainOrderText) {
        this.mainOrderText = mainOrderText;
    }
}
