package com.empapp.controller;

import com.empapp.dto.PayrollItemsDTO;
import com.empapp.model.Employees;
import com.empapp.model.Payroll;
import com.empapp.service.PayrollItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll-items")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PayrollItemsController {

    private final PayrollItemsService service;

    @PostMapping
    public PayrollItemsDTO createPayrollItem(@RequestBody PayrollItemsDTO dto) {
        return service.createPayrollItem(dto);
    }

    @GetMapping
    public List<PayrollItemsDTO> getAllPayrollItems() {
        return service.getAllPayrollItems();
    }

    @GetMapping("/{id}")
    public PayrollItemsDTO getPayrollItemById(@PathVariable Long id) {
        return service.getPayrollItemById(id);
    }

    @GetMapping("/payroll/{payrollId}")
    public List<PayrollItemsDTO> getItemsByPayroll(@PathVariable Long payrollId) {
        Payroll payroll = new Payroll();
        payroll.setPayrollId(payrollId);
        return service.getItemsByPayroll(payroll);
    }

    @GetMapping("/employees/{employeeId}")
    public List<PayrollItemsDTO> getItemsByEmployee(@PathVariable Long employeeId) {
        Employees employee = new Employees();
        employee.setEmployeeId(employeeId);
        return service.getItemsByEmployee(employee);
    }

    @GetMapping("/payroll/{payrollId}/unpaid")
    public List<PayrollItemsDTO> getUnpaidItems(@PathVariable Long payrollId) {
        Payroll payroll = new Payroll();
        payroll.setPayrollId(payrollId);
        return service.getUnpaidItemsByPayroll(payroll);
    }

    @PutMapping("/mark-paid/{itemId}")
    public PayrollItemsDTO markAsPaid(@PathVariable Long itemId) {
        return service.markAsPaid(itemId);
    }

    @DeleteMapping("/{id}")
    public void deletePayrollItem(@PathVariable Long id) {
        service.deletePayrollItem(id);
    }
}
