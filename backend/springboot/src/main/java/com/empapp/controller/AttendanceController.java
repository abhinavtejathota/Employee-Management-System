package com.empapp.controller;

import com.empapp.dto.AttendanceDTO;
import com.empapp.model.Employees;
import com.empapp.service.AttendanceService;
import com.empapp.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final EmployeesRepository employeesRepository;

    @PostMapping
    public AttendanceDTO createAttendance(@RequestBody AttendanceDTO dto) {
        Employees employee = employeesRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + dto.getEmployeeId()));
        return attendanceService.saveAttendance(dto, employee);
    }

    @GetMapping
    public List<AttendanceDTO> getAllAttendance() {
        return attendanceService.getAllAttendances();
    }

    @GetMapping("/{id}")
    public AttendanceDTO getAttendanceById(@PathVariable Long id) {
        return attendanceService.getAttendanceById(id);
    }

    @GetMapping("/employees/{employeeId}")
    public List<AttendanceDTO> getByEmployee(@PathVariable Long employeeId) {
        Employees employee = employeesRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));
        return attendanceService.getAttendancesByEmployee(employee);
    }

    @GetMapping("/date/{date}")
    public List<AttendanceDTO> getByDate(@PathVariable Date date) {
        return attendanceService.getAttendancesByDate(date);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }
}
