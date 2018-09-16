package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/3/16.
 */
@Entity
@Table(name = "PART_TYPE")
@Access(AccessType.PROPERTY)
public class PartType implements EntityModel {

    private Long id;
    private String name;
    private List<PartStore> parts = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PART_TYPE_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long partTypeId) {
        this.id = partTypeId;
    }

    @Column(name = "PART_TYPE_NAME")
    public String getName() {
        return name;
    }

    public void setName(String partTypeName) {
        this.name = partTypeName;
    }

    @OneToMany(mappedBy = "partType", cascade = CascadeType.ALL)
    public List<PartStore> getParts() {
        return parts;
    }

    public void setParts(List<PartStore> parts) {
        this.parts = parts;
    }

    @Override
    public String toString() {
        return "PartType{" +
                "partTypeId=" + id +
                ", partTypeName='" + name + '\'' +
                '}';
    }
}
