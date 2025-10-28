package com.empapp.service;

import com.empapp.dto.LeavesDTO;
import com.empapp.model.Employees;
import com.empapp.model.Leaves;
import com.empapp.repository.LeavesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeavesService {

    private final LeavesRepository repository;

    // CREATE
    public Leaves applyLeave(Leaves leave) {
        return repository.save(leave);
    }

    // READ
    public List<Leaves> getAllLeaves() {
        return repository.findAll();
    }

    public Leaves getLeaveById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Leaves> getLeavesByEmployee(Employees employee) {
        return repository.findByEmployee(employee);
    }

    public List<Leaves> getLeavesByApprover(Employees approvedBy) {
        return repository.findByApprovedBy(approvedBy);
    }

    public List<Leaves> getLeavesByStatus(Leaves.LeaveStatus status) {
        return repository.findByStatus(status);
    }

    // UPDATE - Approve / Reject
    public Leaves approveOrRejectLeave(Long leaveId, Employees approver, Leaves.LeaveStatus status) {
        return repository.findById(leaveId).map(leave -> {
            leave.setApprovedBy(approver);
            leave.setStatus(status);
            leave.setApprovedAt(new java.sql.Timestamp(System.currentTimeMillis()));
            return repository.save(leave);
        }).orElse(null);
    }

    // DELETE
    public void deleteLeave(Long leaveId) {
        repository.deleteById(leaveId);
    }

    // ---------------- Conversion ----------------
    public LeavesDTO toDTO(Leaves leave) {
        LeavesDTO dto = new LeavesDTO();
        dto.setLeaveId(leave.getLeaveId());
        dto.setEmployeeId(leave.getEmployee() != null ? leave.getEmployee().getEmployeeId() : null);
        dto.setEmployeeName(leave.getEmployee() != null ?
                leave.getEmployee().getFirstName() + " " + leave.getEmployee().getLastName() : null);
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setReason(leave.getReason());
        dto.setStatus(leave.getStatus());
        dto.setApprovedById(leave.getApprovedBy() != null ? leave.getApprovedBy().getEmployeeId() : null);
        dto.setApprovedByName(leave.getApprovedBy() != null ?
                leave.getApprovedBy().getFirstName() + " " + leave.getApprovedBy().getLastName() : null);
        dto.setApprovedAt(leave.getApprovedAt());
        return dto;
    }

    public Leaves toEntity(LeavesDTO dto, Employees employee, Employees approvedBy) {
        Leaves leave = new Leaves();
        leave.setLeaveId(dto.getLeaveId());
        leave.setEmployee(employee);
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());
        leave.setStatus(dto.getStatus());
        leave.setApprovedBy(approvedBy);
        leave.setApprovedAt(dto.getApprovedAt());
        return leave;
    }

    // List Conversion
    public List<LeavesDTO> toDTOList(List<Leaves> leaves) {
        return leaves.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
