package com.empapp.service;

import com.empapp.model.EmployeeSkills;
import com.empapp.model.EmployeeSkillId;
import com.empapp.model.Employees;
import com.empapp.model.Skills;
import com.empapp.dto.EmployeeSkillsDTO;
import com.empapp.repository.EmployeeSkillsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeSkillsService {

    private final EmployeeSkillsRepository repository;

    // Entity → DTO
    public EmployeeSkillsDTO toDTO(EmployeeSkills es) {
        EmployeeSkillsDTO dto = new EmployeeSkillsDTO();
        dto.setEmployeeId(es.getEmployee().getEmployeeId());
        dto.setEmployeeName(es.getEmployee().getFirstName() + " " + es.getEmployee().getLastName());
        dto.setSkillId(es.getSkill().getSkillId());
        dto.setSkillName(es.getSkill().getSkillName());
        return dto;
    }

    // DTO → Entity
    public EmployeeSkills toEntity(EmployeeSkillsDTO dto, Employees employee, Skills skill) {
        EmployeeSkills entity = new EmployeeSkills();
        entity.setEmployee(employee);
        entity.setSkill(skill);
        return entity;
    }

    // CREATE
    public EmployeeSkillsDTO addSkillToEmployee(EmployeeSkillsDTO dto, Employees employee, Skills skill) {
        EmployeeSkills saved = repository.save(toEntity(dto, employee, skill));
        return toDTO(saved);
    }

    // READ
    public List<EmployeeSkillsDTO> getSkillsByEmployee(Employees employee) {
        return repository.findByEmployee(employee).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeSkillsDTO> getAllEmployeeSkills() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // DELETE
    public void removeSkillFromEmployee(Long employeeId, Long skillId) {
        EmployeeSkillId id = new EmployeeSkillId(employeeId, skillId);
        repository.deleteById(id);
    }
}
