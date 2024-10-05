package com.gatech.payroll.EmployeePayrollSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="payroll")
@Getter
@Setter
@EqualsAndHashCode
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="gross_pay")
    private double grossPay;

    @Column(name="net_pay")
    private double netPay;

    @Column(name = "pay_period", unique = true)
    private String payPeriod;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

}
