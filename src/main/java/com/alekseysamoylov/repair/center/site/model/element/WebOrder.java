package com.alekseysamoylov.repair.center.site.model.element;

import com.alekseysamoylov.repair.center.site.model.WebElement;

import java.util.Date;

/**
 * Created by alekseysamoylov on 7/6/16.
 * Car
 */
public class WebOrder implements WebElement, Comparable<WebOrder> {

    private Long id;
    private WebCompany webCompany;
    private Date orderDate;
    //Car name
    private String name;
    private String description;
    private String sum;
    private boolean complete;
    private Integer rating;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public WebCompany getWebCompany() {
        return webCompany;
    }

    public void setWebCompany(WebCompany webCompany) {
        this.webCompany = webCompany;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public int compareTo(WebOrder o) {
        return o.getId().compareTo(id);
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((WebOrder)obj).getId());
    }

    @Override
    public String toString() {
        return "WebOrder{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sum='" + sum + '\'' +
                ", complete=" + complete +
                '}';
    }
}
