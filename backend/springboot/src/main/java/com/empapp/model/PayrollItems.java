package com.empapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "payroll_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayrollItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payroll_id", nullable = false)
    private Payroll payroll;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employees employee;
    @Column(name = "gross_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal grossAmount;
    @Column(name = "tax", precision = 12, scale = 2)
    private BigDecimal tax;
    @Column(name = "deductions", precision = 12, scale = 2)
    private BigDecimal deductions;
    @Column(name = "net_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal netAmount;
    @Column(name = "paid")
    private Boolean paid;
    @Column(name = "paid_at")
    private Timestamp paidAt;
    @Column(name = "payment_method", length = 50)
    private String paymentMethod;
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
}
