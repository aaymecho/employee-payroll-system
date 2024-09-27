package com.gatech.payroll.EmployeePayrollSystem.service;

import com.gatech.payroll.EmployeePayrollSystem.model.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public Employee getEmployeeById(Long id);

    public Employee addEmployee(Employee employee);

    public Employee updateEmployee(Long employeeId, Employee employee);

    public void deleteEmployee(Long employeeId);
}
