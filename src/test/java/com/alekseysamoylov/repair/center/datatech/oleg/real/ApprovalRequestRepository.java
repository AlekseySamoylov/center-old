package com.alekseysamoylov.repair.center.datatech.oleg.real;

/**
 * Интерфейс доступа к базе данных для {@link}.
 */
public interface ApprovalRequestRepository {

    /**
     * Поиск запроса на согласование по согласованию и сотруднику
     *
//     * @param approvalId идентификатор согласования
  //   * @param employeeId идентификатор сотрудника
     *
     * @return запрос на согласование
     */
    ApprovalRequest findOneByApprovalIdAndEmployeeId(String mock);
}
