package com.alekseysamoylov.repair.center.site.model.element;

import java.math.BigDecimal;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
public class WebOrderWorkTemplate {
    private Long orderId;
    private Long masterId;
    private Long carModelId;
    private Long sectionId;
    private boolean WorkInPrice;
    private Long priceWorkId;
    private String priceName;
    private BigDecimal priceCost;
    private int priceValue = 1;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Long carModelId) {
        this.carModelId = carModelId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public boolean isWorkInPrice() {
        return WorkInPrice;
    }

    public void setWorkInPrice(boolean workInPrice) {
        WorkInPrice = workInPrice;
    }

    public Long getPriceWorkId() {
        return priceWorkId;
    }

    public void setPriceWorkId(Long priceWorkId) {
        this.priceWorkId = priceWorkId;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public BigDecimal getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(BigDecimal priceCost) {
        this.priceCost = priceCost;
    }

    public int getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(int priceValue) {
        this.priceValue = priceValue;
    }

    @Override
    public String toString() {
        return "WebOrderWorkTemplate{" +
                "orderId=" + orderId +
                ", masterId=" + masterId +
                ", carModelId=" + carModelId +
                ", sectionId=" + sectionId +
                ", WorkInPrice=" + WorkInPrice +
                ", priceWorkId=" + priceWorkId +
                ", priceName='" + priceName + '\'' +
                ", priceCost=" + priceCost +
                ", priceValue=" + priceValue +
                '}';
    }
}
