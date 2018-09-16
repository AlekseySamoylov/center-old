package com.alekseysamoylov.repair.center.site.model.element;

import com.alekseysamoylov.repair.center.site.model.WebElement;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
public class WebWorkPrice implements WebElement, Comparable<WebWorkPrice> {
    private Long id;
    private String name;
    private String description;
    private String section;
    private String car;
    private double price;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(WebWorkPrice o) {
        return section.compareTo(o.getSection());
    }

    @Override
    public String toString() {
        return "WebWorkPrice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", section='" + section + '\'' +
                ", car='" + car + '\'' +
                ", price=" + price +
                '}';
    }
}
