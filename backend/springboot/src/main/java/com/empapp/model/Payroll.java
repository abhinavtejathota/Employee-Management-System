package com.empapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.util.List; 

@Entity
@Table(name = "payroll")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payroll_id")
    private Long payrollId;

    @Column(name = "period_start", nullable = false)
    private LocalDate periodStart;

    @Column(name = "period_end", nullable = false)
    private LocalDate periodEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private Users createdBy;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

	@OneToMany(mappedBy = "payroll", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PayrollItems> payrollItems;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PayrollStatus status = PayrollStatus.PENDING;

    public enum PayrollStatus {
        PENDING,
        APPROVED,
        DISTRIBUTED,
        CANCELLED
    }
}