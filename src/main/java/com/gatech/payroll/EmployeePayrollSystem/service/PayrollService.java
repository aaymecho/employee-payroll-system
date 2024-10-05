package com.gatech.payroll.EmployeePayrollSystem.service;

import com.gatech.payroll.EmployeePayrollSystem.model.Payroll;

import java.util.List;

public interface PayrollService {
    List<Payroll> getAllPayrolls();

    Payroll getPayrollByEmployeeIdAndPayPeriod(Long employeeId, String payPeriod);

    List<Payroll> getPayrollByEmployeeId(Long employeeId);

    Payroll addPayroll(long employeeId, Payroll payroll);

    Payroll updatePayroll(long payrollId, Payroll payrollDetails);

}
