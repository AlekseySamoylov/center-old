package com.alekseysamoylov.repair.center.service;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.PurchaseLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by alekseysamoylov on 7/25/16.
 */
@Service
@Transactional
public class PurchaseLogService {

    private EntityDao<PurchaseLog> purchaseLogEntityDao;

    @Autowired
    public PurchaseLogService(@Qualifier("purchaseLogDaoImpl") EntityDao<PurchaseLog> purchaseLogEntityDao) {
        this.purchaseLogEntityDao = purchaseLogEntityDao;
    }

    @Transactional
    public long log(String text) {
        String tempText = text;
        if (text.length() > 60) tempText = tempText.substring(0, 60);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        PurchaseLog purchaseLog = new PurchaseLog();
        purchaseLog.setLogDate(new Date());
        purchaseLog.setName(sdf.format(cal.getTime())
                + " " + tempText);
        return purchaseLogEntityDao.add(purchaseLog);
    }

    @Transactional(readOnly = true)
    public List<PurchaseLog> getLogList() {
        List<PurchaseLog> list = purchaseLogEntityDao.getAll();
        Collections.sort(list);
        return list;
    }
}
