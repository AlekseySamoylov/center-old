package com.alekseysamoylov.repair.center.site.model.element;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
public class WebManager {
    private Long id;
    private String name;

    public WebManager(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public WebManager() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
