package com.empapp.controller;

import com.empapp.dto.DepartmentsDTO;
import com.empapp.service.DepartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DepartmentsController {

    private final DepartmentsService departmentsService;

    @GetMapping
    public List<DepartmentsDTO> getAllDepartments() {
        return departmentsService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public DepartmentsDTO getDepartmentById(@PathVariable Long id) {
        DepartmentsDTO dto = departmentsService.getDepartmentById(id);
        if (dto == null) {
            throw new RuntimeException("Department not found with ID " + id);
        }
        return dto;
    }

    @PostMapping
    public DepartmentsDTO addDepartment(@RequestBody DepartmentsDTO dto) {
        return departmentsService.addDepartment(dto);
    }

    @PutMapping("/{id}")
    public DepartmentsDTO updateDepartment(@PathVariable Long id, @RequestBody DepartmentsDTO dto) {
        return departmentsService.updateDepartment(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentsService.deleteDepartment(id);
        return "Department with ID " + id + " deleted successfully.";
    }
}
