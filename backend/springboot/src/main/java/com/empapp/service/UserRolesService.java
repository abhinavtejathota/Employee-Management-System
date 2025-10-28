package com.empapp.service;

import com.empapp.dto.UserRolesDTO;
import com.empapp.model.*;
import com.empapp.repository.UserRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRolesService {

    private final UserRolesRepository repository;

    // Assign role
    public UserRolesDTO assignRoleToUser(Users user, Roles role) {
        UserRoleId id = new UserRoleId(user.getUserId(), role.getRoleId());
        UserRoles userRole = new UserRoles(id, user, role);
        return toDTO(repository.save(userRole));
    }

    // Get all roles of a user
    public List<UserRolesDTO> getRolesByUser(Long userId) {
        return repository.findByUserUserId(userId).stream().map(this::toDTO).toList();
    }

    // Get all users with a role
    public List<UserRolesDTO> getUsersByRole(Long roleId) {
        return repository.findByRoleRoleId(roleId).stream().map(this::toDTO).toList();
    }

    // Remove role
    public void removeRoleFromUser(Long userId, Long roleId) {
        repository.deleteById(new UserRoleId(userId, roleId));
    }

    // ---------------- Conversion ----------------
    public UserRolesDTO toDTO(UserRoles ur) {
        if (ur == null) return null;

        UserRolesDTO dto = new UserRolesDTO();
        dto.setUserId(ur.getUser().getUserId());
        dto.setUsername(ur.getUser().getUsername());
        dto.setRoleId(ur.getRole().getRoleId());
        dto.setRoleName(ur.getRole().getRoleName());
        return dto;
    }

    public UserRoles toEntity(UserRolesDTO dto) {
        Users user = new Users();
        user.setUserId(dto.getUserId());
        user.setUsername(dto.getUsername());

        Roles role = new Roles();
        role.setRoleId(dto.getRoleId());
        role.setRoleName(dto.getRoleName());

        UserRoleId id = new UserRoleId(dto.getUserId(), dto.getRoleId());
        return new UserRoles(id, user, role);
    }
}
