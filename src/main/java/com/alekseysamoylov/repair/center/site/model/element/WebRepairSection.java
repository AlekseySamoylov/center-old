package com.alekseysamoylov.repair.center.site.model.element;

import com.alekseysamoylov.repair.center.site.model.WebElement;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
public class WebRepairSection implements WebElement, Comparable<WebRepairSection> {
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
    public int compareTo(WebRepairSection o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "WebSection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
