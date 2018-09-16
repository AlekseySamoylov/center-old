package com.alekseysamoylov.repair.center.site.model.element;

import com.alekseysamoylov.repair.center.site.model.WebElement;
import com.alekseysamoylov.repair.center.site.model.table.WebOrderPartTable;
import com.alekseysamoylov.repair.center.site.model.table.WebOrderWorkTable;

import java.math.BigDecimal;

/**
 * Created by alekseysamoylov on 7/8/16.
 */
public class WebPurchaseOrderTemplate implements WebElement {
    private Long id;
    private Long managerId;
    private Long companyId;
    private String name;
    private String client;
    private String clientPhone;
    private String carName;
    private String managerName;
    //To jsp page only;
    private String checked;
    private BigDecimal prepayment;
    private int discount;

    private boolean complete;

    private boolean workDiscount = true;
    private String discountWorksChecked;

    private boolean partDiscount = false;
    private String discountPartsChecked;

    private String advice;
    private WebOrderWorkTable webOrderWorkTable;
    private WebOrderPartTable webOrderPartTable;

    private BigDecimal worksSum;
    private BigDecimal partsSum;

    private BigDecimal sum;

    private BigDecimal sumWithDiscount;

    private BigDecimal finalSum;

    private String rating;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public BigDecimal getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(BigDecimal prepayment) {
        this.prepayment = prepayment;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isWorkDiscount() {
        return workDiscount;
    }

    public void setWorkDiscount(boolean workDiscount) {
        this.workDiscount = workDiscount;
        this.discountWorksChecked = workDiscount? "checked='checked'" : "";

    }

    public String getDiscountWorksChecked() {
        return discountWorksChecked;
    }

    public void setDiscountWorksChecked(String discountWorksChecked) {
        this.discountWorksChecked = discountWorksChecked;
    }

    public boolean isPartDiscount() {
        return partDiscount;
    }

    public void setPartDiscount(boolean partDiscount) {
        this.partDiscount = partDiscount;
        this.discountPartsChecked = partDiscount? "checked='checked'" : "";

    }

    public String getDiscountPartsChecked() {
        return discountPartsChecked;
    }

    public void setDiscountPartsChecked(String discountPartsChecked) {
        this.discountPartsChecked = discountPartsChecked;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
        this.checked = complete? "checked='checked'" : "";
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public WebOrderWorkTable getWebOrderWorkTable() {
        return webOrderWorkTable;
    }

    public void setWebOrderWorkTable(WebOrderWorkTable webOrderWorkTable) {
        this.webOrderWorkTable = webOrderWorkTable;
    }

    public WebOrderPartTable getWebOrderPartTable() {
        return webOrderPartTable;
    }

    public void setWebOrderPartTable(WebOrderPartTable webOrderPartTable) {
        this.webOrderPartTable = webOrderPartTable;
    }

    public BigDecimal getWorksSum() {
        return worksSum;
    }

    public void setWorksSum(BigDecimal worksSum) {
        this.worksSum = worksSum;
    }

    public BigDecimal getPartsSum() {
        return partsSum;
    }

    public void setPartsSum(BigDecimal partsSum) {
        this.partsSum = partsSum;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getSumWithDiscount() {
        return sumWithDiscount;
    }

    public void setSumWithDiscount(BigDecimal sumWithDiscount) {
        this.sumWithDiscount = sumWithDiscount;
    }

    public BigDecimal getFinalSum() {
        return finalSum;
    }

    public void setFinalSum(BigDecimal finalSum) {
        this.finalSum = finalSum;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
