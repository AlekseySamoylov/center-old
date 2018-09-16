package com.alekseysamoylov.repair.center.site.model.element;

import com.alekseysamoylov.repair.center.site.model.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
public class WebCarMark implements WebElement, Comparable<WebCarMark> {

    private Long id;
    private String name;
    private List<WebCarModel> models = new ArrayList<>();

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

    public List<WebCarModel> getModels() {
        return models;
    }

    public void setModels(List<WebCarModel> models) {
        this.models = models;
    }

    @Override
    public int compareTo(WebCarMark o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "WebCarMark{" +
                "models=" + models +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
