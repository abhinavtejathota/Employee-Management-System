package com.empapp.controller;

import com.empapp.dto.CompensationDTO;
import com.empapp.model.Employees;
import com.empapp.repository.EmployeesRepository;
import com.empapp.service.CompensationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compensation")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CompensationController {

    private final CompensationService compensationService;
    private final EmployeesRepository employeesRepository;

    @PostMapping
    public CompensationDTO createCompensation(@RequestBody CompensationDTO dto) {
        Employees employee = employeesRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + dto.getEmployeeId()));
        return compensationService.createCompensation(dto, employee);
    }

    @GetMapping
    public List<CompensationDTO> getAllCompensations() {
        return compensationService.getAllCompensations();
    }

    @GetMapping("/{id}")
    public CompensationDTO getCompensationById(@PathVariable Long id) {
        return compensationService.getCompensationById(id);
    }

    @GetMapping("/employees/{employeeId}")
    public List<CompensationDTO> getCompensationsByEmployee(@PathVariable Long employeeId) {
        Employees employee = employeesRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));
        return compensationService.getCompensationsByEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteCompensation(@PathVariable Long id) {
        compensationService.deleteCompensation(id);
    }
}
