package com.empapp.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private String maritalStatus;
    private LocalDate hireDate;
    private String employmentType;
    private String jobTitle;
    private Long departmentId;
    private String departmentName;
    private Long managerId;
    private String managerName;
    private String status;
}
