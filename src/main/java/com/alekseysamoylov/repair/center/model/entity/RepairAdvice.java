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
@Table(name = "CAR_REPAIR_ADVICE")
@Access(AccessType.PROPERTY)
public class RepairAdvice implements EntityModel {
    private Long id;
    private RepairSection repairSection;
    private List<CarModel> adviceCarModels = new ArrayList<>();
    private String name;
    private String adviceText;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_REPAIR_ADVICE_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long adviceId) {
        this.id = adviceId;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "CAR_REPAIR_SECTION_ID")
    public RepairSection getRepairSection() {
        return repairSection;
    }

    public void setRepairSection(RepairSection repairSection) {
        this.repairSection = repairSection;
    }

    @ManyToMany
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinTable(
            name = "CAR_MODEL_REPAIR_ADVICE",
            joinColumns = @JoinColumn(name = "CAR_REPAIR_ADVICE_ID"),
            inverseJoinColumns = @JoinColumn(name = "CAR_MODEL_ID"))
    public List<CarModel> getAdviceCarModels() {
        return adviceCarModels;
    }

    public void setAdviceCarModels(List<CarModel> adviceCarModels) {
        this.adviceCarModels = adviceCarModels;
    }

    @Column(name = "CAR_REPAIR_ADVICE_NAME")
    public String getName() {
        return name;
    }

    public void setName(String adviceName) {
        this.name = adviceName;
    }

    @Column(name = "CAR_REPAIR_ADVICE_TEXT")
    public String getAdviceText() {
        return adviceText;
    }

    public void setAdviceText(String adviceText) {
        this.adviceText = adviceText;
    }

    @Override
    public String toString() {
        return "RepairAdvice{" +
                "adviceId=" + id +
                ", repairSection=" + repairSection.getName() +
                ", adviceCarModels=" + adviceCarModels.get(0).getName() +
                ", adviceName='" + name + '\'' +
                ", adviceText='" + adviceText + '\'' +
                '}';
    }
}
