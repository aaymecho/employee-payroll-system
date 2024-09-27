package com.gatech.payroll.EmployeePayrollSystem.service;

import com.gatech.payroll.EmployeePayrollSystem.model.Payroll;
import com.gatech.payroll.EmployeePayrollSystem.repository.EmployeeRepository;
import com.gatech.payroll.EmployeePayrollSystem.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    @Override
    public Payroll getPayrollByEmployeeIdAndPayPeriod(Long employeeId, String payPeriod) {
        return payrollRepository.findByEmployeeIdAndPayPeriod(employeeId, payPeriod);
    }

    @Override
    public List<Payroll> getPayrollsByEmployeeId(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new NoSuchElementException("Employee id not found for getting payrolls.");
        }
        return payrollRepository.findByEmployeeId(employeeId);
    }

    @Override
    public Payroll addPayroll(long employeeId, Payroll payroll) {
        Payroll p = employeeRepository.findById(employeeId).map(employee -> {
            Double grossPay = employee.getSalary();
            Double netPay = employee.getSalary() * 0.8;
            payroll.setEmployee(employee);
            payroll.setGrossPay(grossPay);
            payroll.setNetPay(netPay);
            return payrollRepository.save(payroll);
        }).orElseThrow(() -> new NoSuchElementException("Employee id not for adding payroll."));
        return p;
    }

    @Override
    public Payroll updatePayroll(long payrollId, Payroll payrollDetails) {
        Payroll payroll = payrollRepository.findById(payrollId).orElseThrow(() -> new NoSuchElementException("Payroll id not found."));

        payroll.setGrossPay((!Double.isNaN(payrollDetails.getGrossPay()) ? payrollDetails.getGrossPay() : payroll.getGrossPay()));
        payroll.setNetPay((!Double.isNaN(payrollDetails.getNetPay()) ? payrollDetails.getNetPay() : payroll.getNetPay()));

        return payrollRepository.save(payroll);

    }
}
