package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/3/16.
 */
@Entity
@Table(name = "CAR_MARK")
@Access(AccessType.PROPERTY)
public class CarMark  implements EntityModel {

    private Long id;
    private String name;
    private List<CarModel> models = new ArrayList<>();

    @Id
    @Column(name = "CAR_MARK_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long markId) {
        this.id = markId;
    }

    @Column(name = "CAR_MARK_NAME")
    public String getName() {
        return name;
    }

    public void setName(String markName) {
        this.name = markName;
    }

    @OneToMany(mappedBy = "carMark", cascade = CascadeType.ALL)
    public List<CarModel> getModels() {
        return models;
    }

    public void setModels(List<CarModel> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "CarMark{" +
                "markId=" + id +
                ", markName='" + name + '\'' +
                '}';
    }
}
