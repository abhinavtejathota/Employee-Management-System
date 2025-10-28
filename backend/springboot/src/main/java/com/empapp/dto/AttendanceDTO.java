package com.empapp.dto;

import com.empapp.model.Attendance;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {
    private Long attendanceId;
    private Long employeeId;
    private String employeeName; 
    private Date date;
    private Time checkIn;
    private Time checkOut;
    private BigDecimal hoursWorked;
    private Attendance.Status status;
    private Timestamp createdAt;
}
