package com.gatech.payroll.EmployeePayrollSystem.repository;

import com.gatech.payroll.EmployeePayrollSystem.model.Employee;
import com.gatech.payroll.EmployeePayrollSystem.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
