package com.alekseysamoylov.repair.center.site.model.element;

import com.alekseysamoylov.repair.center.site.model.WebElement;

import java.math.BigDecimal;

/**
 * Created by alekseysamoylov on 7/8/16.
 */
public class WebOrderPart implements WebElement, Comparable<WebOrderPart> {
    private Long id;
    private String name;
    private BigDecimal price;
    private int value;
    private BigDecimal sum;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
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
    public int compareTo(WebOrderPart o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "WebOrderPart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", value=" + value +
                ", sum=" + sum +
                '}';
    }

}
