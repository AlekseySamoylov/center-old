package com.alekseysamoylov.repair.center.site.model.element;

import com.alekseysamoylov.repair.center.site.model.WebElement;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
public class WebClientCar implements WebElement, Comparable<WebClientCar> {

    private Long id;
    private String name;

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

    @Override
    public int compareTo(WebClientCar o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "WebClientCar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
