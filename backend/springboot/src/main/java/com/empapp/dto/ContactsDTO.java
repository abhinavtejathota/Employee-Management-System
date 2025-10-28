package com.empapp.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactsDTO {
    private Long contactId;
    private Long employeeId;
    private String employeeName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String emailOfficial;
    private String emailPersonal;
    private String phoneMobile;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
