package com.empapp.dto;

import com.empapp.model.Payroll.PayrollStatus;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayrollDTO {
    private Long payrollId;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private String createdByUsername; 
	private List<PayrollItemsDTO> payrollItems;
    private Timestamp createdAt;
	@Enumerated(EnumType.STRING)
    private PayrollStatus status;
    private Double totalAmount; 
}
