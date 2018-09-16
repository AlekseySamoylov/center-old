package com.alekseysamoylov.repair.center.site.service.fill;

import com.alekseysamoylov.repair.center.dao.EntityDao;
import com.alekseysamoylov.repair.center.model.entity.Company;
import com.alekseysamoylov.repair.center.model.entity.RepairAccount;
import com.alekseysamoylov.repair.center.site.model.WebTable;
import com.alekseysamoylov.repair.center.site.model.element.WebClient;
import com.alekseysamoylov.repair.center.site.model.element.WebCompany;
import com.alekseysamoylov.repair.center.site.model.table.WebClientTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/7/16.
 */
@Service
@Transactional
public class FillClientList implements FillWebList<WebClient> {

    private final EntityDao<RepairAccount> entityDao;

    @Autowired
    public FillClientList(@Qualifier("repairAccountDaoImpl") EntityDao<RepairAccount> entityDao) {
        this.entityDao = entityDao;
    }

    @Override
    @Transactional(readOnly = true)
    public WebTable<WebClient> getFilledWebList() {
        List<WebClient> webList = new ArrayList<>();
        for (RepairAccount account : entityDao.getAll()) {
            WebClient webClient = new WebClient();
            webClient.setId(account.getId());
            webClient.setName(account
                    .getAccountProperty().getLastName() + " "
                    + account.getAccountProperty().getName());
            webList.add(webClient);
        }
        Collections.sort(webList);
        return new WebClientTable(webList);
    }

    /**
     * only clients in one company
     *
     * @param id Company id
     * @return Clients
     */
    @Override
    @Transactional(readOnly = true)
    public WebTable<WebClient> getFilledWebList(Long id) {
        List<WebClient> webList = new ArrayList<>();
        Company tempCompany = new Company();
        tempCompany.setId(id);
        entityDao.getAll().stream()
                .filter(account -> account
                        .getCompanies().contains(tempCompany))
                .forEach(account -> {
                    WebClient webClient = new WebClient();
                    webClient.setId(account.getId());
                    webClient.setName(account
                            .getAccountProperty().getLastName() + " "
                            + account.getAccountProperty().getName());
                    webList.add(webClient);
                });
        Collections.sort(webList);
        return new WebClientTable(webList);
    }
}
