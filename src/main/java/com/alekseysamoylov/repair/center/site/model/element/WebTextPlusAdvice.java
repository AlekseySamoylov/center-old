package com.alekseysamoylov.repair.center.site.model.element;

import java.math.BigDecimal;

/**
 * Created by alekseysamoylov on 7/8/16.
 */
public class WebTextPlusAdvice {
    private Long id;
    private String text;
    private String advice;
    private BigDecimal prepayment = new BigDecimal(0);
    private int discount = 0;
    private boolean discountWorks;
    private String discountWorksChecked;
    private boolean discountParts;
    private String discountPartsChecked;
    private int operation = 1;
    private BigDecimal worksSum = new BigDecimal(0);
    private BigDecimal partsSum = new BigDecimal(0);
    private BigDecimal noFinalSum = new BigDecimal(0);
    private BigDecimal finalSum = new BigDecimal(0);
    private boolean complete = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
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

    public boolean isDiscountWorks() {
        return discountWorks;
    }

    public void setDiscountWorks(boolean discountWorks) {
        this.discountWorks = discountWorks;
        this.discountWorksChecked = discountWorks? "checked='checked'" : "";
    }

    public String getDiscountWorksChecked() {
        return discountWorksChecked;
    }

    public void setDiscountWorksChecked(String discountWorksChecked) {
        this.discountWorksChecked = discountWorksChecked;
    }

    public boolean isDiscountParts() {
        return discountParts;
    }

    public void setDiscountParts(boolean discountParts) {
        this.discountParts = discountParts;
        this.discountPartsChecked = discountParts? "checked='checked'" : "";
    }

    public String getDiscountPartsChecked() {
        return discountPartsChecked;
    }

    public void setDiscountPartsChecked(String discountPartsChecked) {
        this.discountPartsChecked = discountPartsChecked;

    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
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

    public BigDecimal getNoFinalSum() {
        return noFinalSum;
    }

    public void setNoFinalSum(BigDecimal noFinalSum) {
        this.noFinalSum = noFinalSum;
    }

    public BigDecimal getFinalSum() {
        return finalSum;
    }

    public void setFinalSum(BigDecimal finalSum) {
        this.finalSum = finalSum;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
