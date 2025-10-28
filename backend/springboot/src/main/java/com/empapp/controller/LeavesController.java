package com.empapp.controller;

import com.empapp.dto.LeavesDTO;
import com.empapp.model.Employees;
import com.empapp.model.Leaves;
import com.empapp.service.LeavesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class LeavesController {

    private final LeavesService service;

    @PostMapping
    public LeavesDTO applyLeave(@RequestBody LeavesDTO dto) {
        Employees employee = new Employees();
        employee.setEmployeeId(dto.getEmployeeId());

        Leaves saved = service.applyLeave(service.toEntity(dto, employee, null));
        return service.toDTO(saved);
    }

    @GetMapping
    public List<LeavesDTO> getAllLeaves() {
        return service.toDTOList(service.getAllLeaves());
    }

    @GetMapping("/{id}")
    public LeavesDTO getLeaveById(@PathVariable Long id) {
        Leaves leave = service.getLeaveById(id);
        return leave != null ? service.toDTO(leave) : null;
    }

    @GetMapping("/employees/{employeeId}")
    public List<LeavesDTO> getLeavesByEmployee(@PathVariable Long employeeId) {
        Employees employee = new Employees();
        employee.setEmployeeId(employeeId);
        return service.toDTOList(service.getLeavesByEmployee(employee));
    }

    @GetMapping("/approver/{approverId}")
    public List<LeavesDTO> getLeavesByApprover(@PathVariable Long approverId) {
        Employees approver = new Employees();
        approver.setEmployeeId(approverId);
        return service.toDTOList(service.getLeavesByApprover(approver));
    }

    @GetMapping("/status/{status}")
    public List<LeavesDTO> getLeavesByStatus(@PathVariable Leaves.LeaveStatus status) {
        return service.toDTOList(service.getLeavesByStatus(status));
    }

    @PutMapping("/{id}/approve")
    public LeavesDTO approveLeave(@PathVariable Long id, @RequestParam Long approverId) {
        Employees approver = new Employees();
        approver.setEmployeeId(approverId);
        Leaves updated = service.approveOrRejectLeave(id, approver, Leaves.LeaveStatus.APPROVED);
        return updated != null ? service.toDTO(updated) : null;
    }

    @PutMapping("/{id}/reject")
    public LeavesDTO rejectLeave(@PathVariable Long id, @RequestParam Long approverId) {
        Employees approver = new Employees();
        approver.setEmployeeId(approverId);
        Leaves updated = service.approveOrRejectLeave(id, approver, Leaves.LeaveStatus.REJECTED);
        return updated != null ? service.toDTO(updated) : null;
    }

    @DeleteMapping("/{id}")
    public void deleteLeave(@PathVariable Long id) {
        service.deleteLeave(id);
    }
}
