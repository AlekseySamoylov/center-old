package com.alekseysamoylov.repair.center.model.entity;

import com.alekseysamoylov.repair.center.model.EntityModel;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 6/26/16.
 */
@Entity
@Table(name = "CAR_REPAIR_ACCOUNT_ROLE")
@Access(value = AccessType.PROPERTY)
public class RepairAccountRole implements EntityModel {

    private static final Logger LOGGER = Logger.getLogger(RepairAccountRole.class);
    private Long id;
    private String name;
    private List<RepairAccount> repairAccounts = new ArrayList<>();

    @Id
    @Column(name = "CAR_REPAIR_ACCOUNT_ROLE_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long roleId) {
        this.id = roleId;
    }

    @Column(name = "CAR_REPAIR_ACCOUNT_ROLE_NAME")
    public String getName() {
        return name;
    }

    public void setName(String roleName) {
        this.name = roleName;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "repairAccountRole", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<RepairAccount> getRepairAccounts() {
        return repairAccounts;
    }

    public void setRepairAccounts(List<RepairAccount> repairAccounts) {
        this.repairAccounts = repairAccounts;
    }

    @Override
    public String toString() {
        return "RepairAccountRole{" +
                "roleId=" + id +
                ", roleName='" + name + '\'' +
                '}';
    }

}
