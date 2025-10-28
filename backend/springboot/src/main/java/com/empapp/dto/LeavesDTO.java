package com.empapp.dto;

import java.time.LocalDate;
import java.sql.Timestamp;
import com.empapp.model.Leaves;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeavesDTO {
    private Long leaveId;
    private Long employeeId;
    private String employeeName; 
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
	@Enumerated(EnumType.STRING)
    private Leaves.LeaveStatus status;
    private Timestamp appliedAt;
    private Long approvedById; 
    private String approvedByName; 
    private Timestamp approvedAt;
    private String reason;
}
