package com.alekseysamoylov.repair.center;

import com.alekseysamoylov.repair.center.service.MyConfiguration;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by alekseysamoylov on 7/4/16.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
    @Ignore
public class Main {

//    @Autowired
//    HibernateTemplate hibernateTemplate;
//
//    @Test
//    @Transactional(readOnly = true)
//    public void connection() {
//        RepairAccount repairAccount = hibernateTemplate.get(RepairAccount.class, (long)-3);
//        CarModel carModel = hibernateTemplate.get(CarModel.class, (long)-1);
//        System.out.println(carModel);
//        System.out.println(carModel.getRepairSections().get(0).getRepairPrices().get(0).getRepairAdvice());
//
//
//
//        Assert.assertNotNull(repairAccount.getAccountCars().get(0));
//    }

    @Test
    public void compared() {
        MyConfiguration propertiesServ = new MyConfiguration();
            System.out.println(propertiesServ.getClientRoleId());
    }
}
