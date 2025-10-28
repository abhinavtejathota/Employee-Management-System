package com.empapp.controller;

import com.empapp.dto.PayrollDTO;
import com.empapp.model.Payroll;
import com.empapp.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payrolls")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollService service;

    @PostMapping
    public PayrollDTO createPayroll(@RequestBody PayrollDTO dto) {
        return service.createPayroll(dto);
    }

    @GetMapping
    public List<PayrollDTO> getAllPayrolls() {
        return service.getAllPayrolls();
    }

    @GetMapping("/{id}")
    public PayrollDTO getPayrollById(@PathVariable Long id) {
        return service.getPayrollById(id);
    }

    @GetMapping("/users/{userId}")
    public List<PayrollDTO> getPayrollsByUser(@PathVariable Long userId) {
        return service.getPayrollsByUser(userId);
    }

    @GetMapping("/status/{status}")
    public List<PayrollDTO> getPayrollsByStatus(@PathVariable String status) {
        Payroll.PayrollStatus payrollStatus = Payroll.PayrollStatus.valueOf(status.toUpperCase());
        return service.getPayrollsByStatus(payrollStatus);
    }

    @PutMapping("/{id}/status")
    public PayrollDTO updateStatus(@PathVariable Long id, @RequestParam String status) {
        Payroll.PayrollStatus payrollStatus = Payroll.PayrollStatus.valueOf(status.toUpperCase());
        return service.updateStatus(id, payrollStatus);
    }

    @DeleteMapping("/{id}")
    public void deletePayroll(@PathVariable Long id) {
        service.deletePayroll(id);
    }
}
