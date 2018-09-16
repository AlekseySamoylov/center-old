package com.alekseysamoylov.repair.center.site.model.element;

import com.alekseysamoylov.repair.center.site.model.WebElement;

import java.math.BigDecimal;

/**
 * Created by alekseysamoylov on 7/8/16.
 */
public class WebOrderWork implements WebElement, Comparable<WebOrderWork> {
    private Long id;
    private String name;
    private String master;
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

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public int compareTo(WebOrderWork o) {
        return master.compareTo(o.getMaster());
    }

    @Override
    public String toString() {
        return "WebOrderWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", master='" + master + '\'' +
                ", price=" + price +
                ", value=" + value +
                ", sum=" + sum +
                '}';
    }
}
