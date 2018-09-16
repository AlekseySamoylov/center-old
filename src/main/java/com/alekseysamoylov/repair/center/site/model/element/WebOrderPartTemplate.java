package com.alekseysamoylov.repair.center.site.model.element;

import java.math.BigDecimal;

/**
 * Created by alekseysamoylov on 7/10/16.
 */
public class WebOrderPartTemplate {
    private Long orderId;
    private String name;
    private BigDecimal price;
    private int value;
    private BigDecimal sum;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }


    @Override
    public String toString() {
        return "WebOrderPartTemplate{" +
                "orderId=" + orderId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", value=" + value +
                ", sum=" + sum +
                '}';
    }
}
