//Attendance, Employees -> AttendanceRepository -> Attendance Service, Attendance Repository
package com.empapp.repository;

import com.empapp.model.Attendance;
import com.empapp.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Date;
import java.util.List;

//Repository layer = Data Access Layer
//Connects directly to the database using JPA
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	//used in attendance service
	//Custom query method: find all attendance for a specific employee
    List<Attendance> findByEmployee(Employees employee);
	//Custom query method: find all attendance records for a specific date
    List<Attendance> findByDate(Date date);
}
