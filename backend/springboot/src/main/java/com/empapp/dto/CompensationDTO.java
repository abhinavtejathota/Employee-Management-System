package com.empapp.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompensationDTO {
    private Long compId;
    private Long employeeId;
    private String employeeName;
    private BigDecimal salary;
    private String payFrequency;
    private String bankAccount;
    private String taxId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
