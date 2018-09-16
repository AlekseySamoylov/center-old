package com.alekseysamoylov.repair.center.datatech.oleg.tests;

import com.alekseysamoylov.repair.center.datatech.oleg.context.TestContextDatatech;
import com.alekseysamoylov.repair.center.datatech.oleg.real.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by alekseysamoylov on 8/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContextDatatech.class)
public class ApprovalPermissionTest {

    @Test
    public void test1() {
        ApprovalPermissionNoAbstractTest approvalPermission = new ApprovalPermissionNoAbstractTest(1L);
        ApprovalPermission.ApprovePermission apprP = (ApprovalPermission.ApprovePermission) approvalPermission.APPROVE;

        try {
            Class c = Class.forName("com.alekseysamoylov.repair.center.datatech.oleg.real.ApprovalPermission$ApprovePermission");
            /**
             * "Custom Mock". Set new custom object with mockito logic into field!!! {@link ApprovalRequestRepositoryTest}
             */
            Field field = c.getSuperclass().getDeclaredField("approvalRequestRepository");
            field.setAccessible(true);
            field.set(apprP, new ApprovalRequestRepositoryTest());
            //Calling protected method from the final class
            Method method = c.getDeclaredMethod("isGrantedInternal", User.class, Approval.class);
            method.setAccessible(true);
            //invoke method with needed parameters (delete "new" and set proper parameters).
            boolean value = (boolean) method.invoke(apprP, new User(), new Approval());
            //Check your test!
            Assert.assertTrue(!value);
        } catch (NoSuchMethodException | InvocationTargetException | ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }



    //Created class for access to creation real object for Abstract class
    public class ApprovalPermissionNoAbstractTest extends ApprovalPermission {
        @Autowired
        ApprovalRequestRepository approvalRequestRepository;

        ApprovalPermissionNoAbstractTest(long mask) {
            super(mask);
        }

        @Override
        public boolean isGranted(User user, Approval targetDomainObject) {
            return isGrantedInternal(user, targetDomainObject);
        }

        @Override
        protected boolean isGrantedInternal(User user, Approval targetDomainObject) {
            return false;
        }

    }

    //Hand-Made Mock - class with custom hardcoded logic.
    public class ApprovalRequestRepositoryTest implements ApprovalRequestRepository {
        //You should hardcode this object.
        ApprovalRequest approvalRequest = new ApprovalRequest();

        //return the Mock - ApprovalRequest
        @Override
        public ApprovalRequest findOneByApprovalIdAndEmployeeId(String mock) {
            return approvalRequest;
        }
    }




}
