package com.empapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "leaves")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leaves {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_id")
    private Long leaveId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employees employee;
    @Column(name = "leave_type", length = 50)
    private String leaveType;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LeaveStatus status = LeaveStatus.REQUESTED;
    @Column(name = "applied_at", insertable = false, updatable = false)
    private Timestamp appliedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private Employees approvedBy;
    @Column(name = "approved_at")
    private Timestamp approvedAt;
    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;
    public enum LeaveStatus {
        REQUESTED,
        APPROVED,
        REJECTED,
        CANCELLED
    }
}
