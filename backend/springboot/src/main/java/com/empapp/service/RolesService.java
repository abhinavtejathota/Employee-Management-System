package com.empapp.service;

import com.empapp.model.Roles;
import com.empapp.dto.RolesDTO;
import com.empapp.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolesService {

    private final RolesRepository repository;

    // Entity → DTO
    public RolesDTO toDTO(Roles role) {
        if (role == null) return null;
        RolesDTO dto = new RolesDTO();
        dto.setRoleId(role.getRoleId());
        dto.setRoleName(role.getRoleName());
        dto.setDescription(role.getDescription());
        return dto;
    }

    // DTO → Entity
    public Roles toEntity(RolesDTO dto) {
        Roles role = new Roles();
        role.setRoleId(dto.getRoleId());
        role.setRoleName(dto.getRoleName());
        role.setDescription(dto.getDescription());
        return role;
    }

    // Create
    public RolesDTO createRole(RolesDTO dto) {
        Roles saved = repository.save(toEntity(dto));
        return toDTO(saved);
    }

    // Read all
    public List<RolesDTO> getAllRoles() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Read one
    public RolesDTO getRoleById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    public RolesDTO getRoleByName(String roleName) {
        return repository.findByRoleName(roleName)
                .map(this::toDTO)
                .orElse(null);
    }

    // Update
    public RolesDTO updateRole(Long roleId, RolesDTO dto) {
        return repository.findById(roleId)
                .map(role -> {
                    role.setRoleName(dto.getRoleName());
                    role.setDescription(dto.getDescription());
                    return toDTO(repository.save(role));
                })
                .orElse(null);
    }

    // Delete
    public void deleteRole(Long roleId) {
        repository.deleteById(roleId);
    }
}
