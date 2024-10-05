package com.gatech.payroll.EmployeePayrollSystem.controller;

import com.gatech.payroll.EmployeePayrollSystem.model.Payroll;
import com.gatech.payroll.EmployeePayrollSystem.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PayrollController {
    @Autowired
    private PayrollService payrollService;

    @PostMapping("payroll/{employeeId}")
    public ResponseEntity<Payroll> newPayroll(@RequestBody Payroll newPayroll, @PathVariable int employeeId) {
        Payroll payroll = payrollService.addPayroll(employeeId, newPayroll);
        return new ResponseEntity<>(payroll, HttpStatus.CREATED);
    }

    @GetMapping("payroll/{employeeId}")
    public ResponseEntity<List<Payroll>> getPayrollByEmployeeId(@PathVariable Long employeeId) {
        return new ResponseEntity<>(payrollService.getPayrollByEmployeeId(employeeId), HttpStatus.OK);
    }

    @GetMapping("payroll")
    public ResponseEntity<List<Payroll>> getAllPayrolls() {
        return new ResponseEntity<>(payrollService.getAllPayrolls(), HttpStatus.OK);
    }

    @GetMapping("payroll/{employeeId}/{payPeriod}")
    public ResponseEntity<Payroll> getPayroll(@PathVariable Long employeeId, @PathVariable String payPeriod) {
        return new ResponseEntity<>(payrollService.getPayrollByEmployeeIdAndPayPeriod(employeeId, payPeriod), HttpStatus.OK);
    }

    


}
