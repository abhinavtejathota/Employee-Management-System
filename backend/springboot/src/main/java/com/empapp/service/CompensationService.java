package com.empapp.service;

import com.empapp.model.Compensation;
import com.empapp.model.Employees;
import com.empapp.dto.CompensationDTO;
import com.empapp.repository.CompensationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompensationService {

    private final CompensationRepository repository;

    // ------------------- Entity <-> DTO -------------------

    public CompensationDTO toDTO(Compensation comp) {
        CompensationDTO dto = new CompensationDTO();
        dto.setCompId(comp.getCompId());
        dto.setEmployeeId(comp.getEmployee().getEmployeeId());
        dto.setEmployeeName(comp.getEmployee().getFirstName() + " " + comp.getEmployee().getLastName());
        dto.setSalary(comp.getSalary());
        dto.setPayFrequency(comp.getPayFrequency());
        dto.setBankAccount(comp.getBankAccount());
        dto.setTaxId(comp.getTaxId());
        dto.setCreatedAt(comp.getCreatedAt());
        dto.setUpdatedAt(comp.getUpdatedAt());
        return dto;
    }

    public Compensation toEntity(CompensationDTO dto, Employees employee) {
        Compensation comp = new Compensation();
        comp.setCompId(dto.getCompId());
        comp.setEmployee(employee);
        comp.setSalary(dto.getSalary());
        comp.setPayFrequency(dto.getPayFrequency());
        comp.setBankAccount(dto.getBankAccount());
        comp.setTaxId(dto.getTaxId());
        return comp;
    }

    // ------------------- CREATE -------------------

    public CompensationDTO createCompensation(CompensationDTO dto, Employees employee) {
        Compensation saved = repository.save(toEntity(dto, employee));
        return toDTO(saved);
    }

    // ------------------- READ -------------------

    public List<CompensationDTO> getAllCompensations() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CompensationDTO getCompensationById(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public List<CompensationDTO> getCompensationsByEmployee(Employees employee) {
        return repository.findByEmployee(employee).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ------------------- DELETE -------------------

    public void deleteCompensation(Long id) {
        repository.deleteById(id);
    }
}
