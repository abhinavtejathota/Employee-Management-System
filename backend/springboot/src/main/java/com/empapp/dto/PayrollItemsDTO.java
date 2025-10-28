package com.empapp.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayrollItemsDTO {
    private Long itemId;
    private Long payrollId;
    private Long employeeId;
    private String employeeName;
    private BigDecimal grossAmount;
    private BigDecimal tax;
    private BigDecimal deductions;
    private BigDecimal netAmount;
    private Boolean paid;
    private String paymentMethod;
    private String notes;
	private Timestamp paidAt;
}
