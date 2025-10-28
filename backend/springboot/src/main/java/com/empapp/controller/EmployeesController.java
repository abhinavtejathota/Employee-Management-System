package com.empapp.controller;

import com.empapp.dto.EmployeeDTO;
import com.empapp.model.Departments;
import com.empapp.model.Employees;
import com.empapp.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeesService employeeService;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeesById(@PathVariable Long id) {
        return employeeService.getEmployeesById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID " + id));
    }

    @PostMapping
    public EmployeeDTO addEmployees(@RequestBody EmployeeDTO dto) {
        Departments dept = null;
        if (dto.getDepartmentId() != null) {
            dept = new Departments();
            dept.setDepartmentId(dto.getDepartmentId());
        }

        Employees manager = null;
        if (dto.getManagerId() != null) {
            manager = new Employees();
            manager.setEmployeeId(dto.getManagerId());
        }

        return employeeService.addEmployees(dto, dept, manager);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployees(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
        Departments dept = null;
        if (dto.getDepartmentId() != null) {
            dept = new Departments();
            dept.setDepartmentId(dto.getDepartmentId());
        }

        Employees manager = null;
        if (dto.getManagerId() != null) {
            manager = new Employees();
            manager.setEmployeeId(dto.getManagerId());
        }

        return employeeService.updateEmployees(id, dto, dept, manager);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployees(@PathVariable Long id) {
        employeeService.deleteEmployees(id);
        return "Employee with ID " + id + " deleted successfully.";
    }
}
