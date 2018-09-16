package com.alekseysamoylov.repair.center.datatech.oleg.real;

/**
 * Created by alekseysamoylov on 8/10/16.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;


/**
 * Реализация прав для запросов на согласование
 */
@Configurable
public abstract class ApprovalPermission extends AbstractPermission<Approval> {

    @Autowired
    ApprovalRequestRepository approvalRequestRepository;

    public ApprovalPermission(long mask) {
        super(mask);
    }

    /**
     * Возможность отклонить/утвердить запрос на согласование
     */
    public static final Permission<Approval> APPROVE = new ApprovePermission(1 << 0);

    // Возможность отклонить/утвердить запрос на согласование
    public static final class ApprovePermission extends ApprovalPermission {

        public ApprovePermission(long mask) {
            super(mask);
        }

        @Override
        protected boolean isGrantedInternal(User user, Approval approval) {
            System.out.println("Test works!");

            System.out.println("Created mocks for this objects and clean comments");
//            ApprovalRequest approvalRequest = approvalRequestRepository.findOneByApprovalIdAndEmployeeId(approval.getId(), user.getEmployee().getId());
//            if (approvalRequest instanceof ApprovalRequest) {
//                if (ApprovalRequestStateEnum.SENDED.equals(approvalRequest.getState())) {
//                    return true;
//                }
//            }
            return false;
        }
    }
}
