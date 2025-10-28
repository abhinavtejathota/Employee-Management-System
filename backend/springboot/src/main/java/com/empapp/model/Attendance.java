//All files -> Attendance
package com.empapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity //Maps this class to a database table
@Table(name = "attendance") //Table name: attendance
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    //Primary key mapping
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long attendanceId; //used in attendancecontroller,attendance service

    //Many-to-One relationship mapping (Attendance → Employees)
    //Many attendance records belong to one employee
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false) // foreign key in DB
    private Employees employee;

    //Date of attendance
    @Column(nullable = false)
    private Date date; //used in attendancecontroller, repository and service

    //Time tracking fields
	//used in attendancecontroller, attendanceservice
    private Time checkIn; 
    private Time checkOut; 
    private BigDecimal hoursWorked; 

    //Enum stored as text ("PRESENT", "ABSENT", "ON_LEAVE")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PRESENT; //used in attendancecontroller, attendanceservice

    //Auto-generated timestamp
    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    //Enum for attendance status
    public enum Status {
        PRESENT,
        ABSENT,
        ON_LEAVE
    }
}
