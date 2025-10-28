package com.empapp.service;

import com.empapp.model.Departments;
import com.empapp.dto.DepartmentsDTO;
import com.empapp.repository.DepartmentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentsService {

    private final DepartmentsRepository repository;

    // Entity → DTO
    public DepartmentsDTO toDTO(Departments dept) {
        DepartmentsDTO dto = new DepartmentsDTO();
        dto.setDepartmentId(dept.getDepartmentId());
        dto.setDepartmentName(dept.getDepartmentName());
        return dto;
    }

    // DTO → Entity
    public Departments toEntity(DepartmentsDTO dto) {
        Departments dept = new Departments();
        dept.setDepartmentId(dto.getDepartmentId());
        dept.setDepartmentName(dto.getDepartmentName());
        return dept;
    }

    public DepartmentsDTO addDepartment(DepartmentsDTO dto) {
        Departments saved = repository.save(toEntity(dto));
        return toDTO(saved);
    }

    public List<DepartmentsDTO> getAllDepartments() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public DepartmentsDTO getDepartmentById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    public DepartmentsDTO updateDepartment(Long id, DepartmentsDTO dto) {
        Departments updated = repository.findById(id)
                .map(existing -> {
                    existing.setDepartmentName(dto.getDepartmentName());
                    return repository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Department not found with ID " + id));
        return toDTO(updated);
    }

    public void deleteDepartment(Long id) {
        repository.deleteById(id);
    }
	
	public List<DepartmentsDTO> toDTOList(List<Departments> departments) {
    return departments.stream()
            .map(this::toDTO)
            .toList(); 
	}
}
