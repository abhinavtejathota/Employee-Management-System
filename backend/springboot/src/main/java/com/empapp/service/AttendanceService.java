package com.empapp.service;

import com.empapp.model.Attendance;
import com.empapp.model.Employees;
import com.empapp.dto.AttendanceDTO;
import com.empapp.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    // ----------------- Convert Entity → DTO --------------------------
    public AttendanceDTO toDTO(Attendance attendance) {
        AttendanceDTO dto = new AttendanceDTO();
        dto.setAttendanceId(attendance.getAttendanceId());
        dto.setEmployeeId(attendance.getEmployee().getEmployeeId());
        dto.setEmployeeName(attendance.getEmployee().getFirstName() + " " + attendance.getEmployee().getLastName());
        dto.setDate(attendance.getDate());
        dto.setCheckIn(attendance.getCheckIn());
        dto.setCheckOut(attendance.getCheckOut());
        dto.setHoursWorked(attendance.getHoursWorked());
        dto.setStatus(attendance.getStatus());
        dto.setCreatedAt(attendance.getCreatedAt());
        return dto;
    }

    // --------------------- Convert DTO → Entity ------------------------
    public Attendance toEntity(AttendanceDTO dto, Employees employee) {
        Attendance attendance = new Attendance();
        attendance.setAttendanceId(dto.getAttendanceId());
        attendance.setEmployee(employee); 
        attendance.setDate(dto.getDate());
        attendance.setCheckIn(dto.getCheckIn());
        attendance.setCheckOut(dto.getCheckOut());
        attendance.setHoursWorked(dto.getHoursWorked());
        attendance.setStatus(dto.getStatus());
        return attendance;
    }

    // Save attendance
    public AttendanceDTO saveAttendance(AttendanceDTO dto, Employees employee) {
        Attendance attendance = toEntity(dto, employee);
        return toDTO(attendanceRepository.save(attendance));
    }

    // Get all attendances
    public List<AttendanceDTO> getAllAttendances() {
        return attendanceRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Get by ID 
    public AttendanceDTO getAttendanceById(Long id) {
        return attendanceRepository.findById(id).map(this::toDTO).orElse(null);
    }

    // Get by Employee 
    public List<AttendanceDTO> getAttendancesByEmployee(Employees employee) {
        return attendanceRepository.findByEmployee(employee).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Get by Date 
    public List<AttendanceDTO> getAttendancesByDate(Date date) {
        return attendanceRepository.findByDate(date).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Delete
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
}
