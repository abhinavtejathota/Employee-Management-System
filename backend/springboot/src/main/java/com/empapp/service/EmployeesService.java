package com.empapp.service;

import com.empapp.model.Employees;
import com.empapp.model.Departments;
import com.empapp.dto.EmployeeDTO;
import com.empapp.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeesService {

    private final EmployeesRepository repository;

    // Entity → DTO
    public EmployeeDTO toDTO(Employees e) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(e.getEmployeeId());
        dto.setFirstName(e.getFirstName());
        dto.setLastName(e.getLastName());
        dto.setDateOfBirth(e.getDateOfBirth());
        dto.setGender(e.getGender());
        dto.setNationality(e.getNationality());
        dto.setMaritalStatus(e.getMaritalStatus());
        dto.setHireDate(e.getHireDate());
        dto.setEmploymentType(e.getEmploymentType());
        dto.setJobTitle(e.getJobTitle());
        dto.setStatus(e.getStatus());

        if (e.getDepartment() != null) {
            dto.setDepartmentId(e.getDepartment().getDepartmentId());
            dto.setDepartmentName(e.getDepartment().getDepartmentName());
        }

        if (e.getManager() != null) {
            dto.setManagerId(e.getManager().getEmployeeId());
            dto.setManagerName(e.getManager().getFirstName() + " " + e.getManager().getLastName());
        }

        return dto;
    }

    // DTO → Entity
    public Employees toEntity(EmployeeDTO dto, Departments dept, Employees manager) {
        Employees e = new Employees();
        e.setEmployeeId(dto.getEmployeeId());
        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setDateOfBirth(dto.getDateOfBirth());
        e.setGender(dto.getGender());
        e.setNationality(dto.getNationality());
        e.setMaritalStatus(dto.getMaritalStatus());
        e.setHireDate(dto.getHireDate());
        e.setEmploymentType(dto.getEmploymentType());
        e.setJobTitle(dto.getJobTitle());
        e.setStatus(dto.getStatus());
        e.setDepartment(dept);
        e.setManager(manager);
        return e;
    }

    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<EmployeeDTO> getEmployeesById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    public EmployeeDTO addEmployees(EmployeeDTO dto, Departments dept, Employees manager) {
        Employees saved = repository.save(toEntity(dto, dept, manager));
        return toDTO(saved);
    }

    public EmployeeDTO updateEmployees(Long id, EmployeeDTO dto, Departments dept, Employees manager) {
        Employees saved = repository.findById(id)
                .map(existing -> {
                    existing.setFirstName(dto.getFirstName());
                    existing.setLastName(dto.getLastName());
                    existing.setDateOfBirth(dto.getDateOfBirth());
                    existing.setGender(dto.getGender());
                    existing.setNationality(dto.getNationality());
                    existing.setMaritalStatus(dto.getMaritalStatus());
                    existing.setHireDate(dto.getHireDate());
                    existing.setEmploymentType(dto.getEmploymentType());
                    existing.setJobTitle(dto.getJobTitle());
                    existing.setDepartment(dept);
                    existing.setManager(manager);
                    existing.setStatus(dto.getStatus());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        return toDTO(saved);
    }

    public void deleteEmployees(Long id) {
        repository.deleteById(id);
    }
}
