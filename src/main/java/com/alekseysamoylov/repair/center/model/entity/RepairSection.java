package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/3/16.
 */
@Entity
@Table(name = "CAR_REPAIR_SECTION")
@Access(AccessType.PROPERTY)
public class RepairSection implements EntityModel {

    private Long id;
    private List<CarModel> repairSectionCarModels = new ArrayList<>();
    private String name;
    private List<RepairPrice> repairPrices = new ArrayList<>();
    private List<RepairAdvice> repairAdvices = new ArrayList<>();

    @Id
    @Column(name = "CAR_REPAIR_SECTION_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long sectionId) {
        this.id = sectionId;
    }

    @ManyToMany
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinTable(
            name = "CAR_MODEL_REPAIR_SECTION",
            joinColumns = @JoinColumn(name = "CAR_REPAIR_SECTION_ID"),
            inverseJoinColumns = @JoinColumn(name = "CAR_MODEL_ID"))
    public List<CarModel> getRepairSectionCarModels() {
        return repairSectionCarModels;
    }

    public void setRepairSectionCarModels(List<CarModel> repairSectionCarModel) {
        this.repairSectionCarModels = repairSectionCarModel;
    }

    @Column(name = "CAR_REPAIR_SECTION_NAME")
    public String getName() {
        return name;
    }

    public void setName(String sectionName) {
        this.name = sectionName;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "repairSection")
    public List<RepairPrice> getRepairPrices() {
        return repairPrices;
    }

    public void setRepairPrices(List<RepairPrice> repairPrices) {
        this.repairPrices = repairPrices;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "repairSection")
    public List<RepairAdvice> getRepairAdvices() {
        return repairAdvices;
    }

    public void setRepairAdvices(List<RepairAdvice> repairAdvices) {
        this.repairAdvices = repairAdvices;
    }

    @Override
    public String toString() {
        return "RepairSection{" +
                "sectionId=" + id +
                ", sectionName='" + name + '\'' +
                '}';
    }
}
