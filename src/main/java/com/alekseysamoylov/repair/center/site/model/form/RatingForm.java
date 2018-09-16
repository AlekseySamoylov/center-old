package com.alekseysamoylov.repair.center.site.model.form;

import com.alekseysamoylov.repair.center.model.entity.Company;

/**
 * Created by alekseysamoylov on 7/31/16.
 */
public class RatingForm {
    private Long id;
    private int stars;
    private String comment;

    public RatingForm() {
    }

    public RatingForm(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        return (int) (id + stars);
    }

    @Override
    public boolean equals(Object obj) {
        return ((RatingForm)obj).getId().equals(id) && ((RatingForm)obj).getStars() == stars;
    }

    @Override
    public String toString() {
        return "RatingForm{" +
                "id=" + id +
                ", stars=" + stars +
                ", comment='" + comment + '\'' +
                '}';
    }
}
