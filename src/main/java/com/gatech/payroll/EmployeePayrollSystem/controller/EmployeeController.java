package com.gatech.payroll.EmployeePayrollSystem.controller;

import com.gatech.payroll.EmployeePayrollSystem.model.Employee;
import com.gatech.payroll.EmployeePayrollSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("employee")
    public ResponseEntity<Employee> newEmployee(@RequestBody Employee newEmployee) {
        Employee employee = employeeService.addEmployee(newEmployee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("employee")
    public ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<Employee> removeEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee emp = employeeService.updateEmployee(id, employeeDetails);
        return new ResponseEntity<>(emp, HttpStatus.OK);

    }
}