package com.alekseysamoylov.repair.center.dao;

import com.alekseysamoylov.repair.center.model.entity.RepairAccount;

/**
 * Created by alekseysamoylov on 7/15/16.
 */
public interface AccountDao {
    RepairAccount findByName(String name);
}
