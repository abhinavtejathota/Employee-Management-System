package com.empapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSkillsDTO {
    private Long employeeId;
    private String employeeName; 
    private Long skillId;
    private String skillName;
}
