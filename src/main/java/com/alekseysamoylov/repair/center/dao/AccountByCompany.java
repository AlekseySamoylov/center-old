package com.alekseysamoylov.repair.center.dao;

import com.alekseysamoylov.repair.center.model.entity.RepairAccount;

import java.util.List;

/**
 * Created by alekseysamoylov on 8/6/16.
 */
public interface AccountByCompany {
    /**
     *
     * @return List of RepairAccounts
     */
    List<RepairAccount> getAllManagers();

    /**
     *
     * @return List of RepairAccounts
     */
    List<RepairAccount> getAllMasters();

    /**
     * Unusable!!!
     * @return null!!
     */
    List<RepairAccount> getAllClients();

}
