package com.gatech.payroll.EmployeePayrollSystem.repository;

import com.gatech.payroll.EmployeePayrollSystem.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findByEmployeeId(Long employeeId);

    Payroll findByEmployeeIdAndPayPeriod(Long employeeId, String payPeriod);

    @Query("select case when count(p) > 0 then true else false end from Payroll p where p.payPeriod = :payPeriod")
    Boolean existsByPayPeriod(@Param("payPeriod") String payPeriod);
}
