package com.empapp.service;

import com.empapp.dto.UserDTO;
import com.empapp.model.Employees;
import com.empapp.model.Roles;
import com.empapp.model.Users;
import com.empapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    // ---------------- CRUD Methods ----------------
    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Optional<Users> getUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public List<Users> getUsersByEmployee(Employees employee) {
        return usersRepository.findByEmployee(employee);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    // ---------------- DTO Conversions ----------------

    // Entity -> DTO
    public UserDTO toDTO(Users user) {
        if (user == null) return null;

        Long employeeId = (user.getEmployee() != null) ? user.getEmployee().getEmployeeId() : null;
        Long roleId = (user.getRole() != null) ? user.getRole().getRoleId() : null;
        String roleName = (user.getRole() != null) ? user.getRole().getRoleName() : null;

        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setEmployeeId(employeeId);
        dto.setRoleId(roleId);
        dto.setRoleName(roleName);
        dto.setEnabled(user.isEnabled());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }

    // DTO -> Entity (basic)
    public Users toEntity(UserDTO dto) {
        if (dto == null) return null;

        Users user = new Users();
        user.setUserId(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setEnabled(dto.isEnabled());
        return user;
    }

    // DTO -> Entity with employee & role
    public Users toEntity(UserDTO dto, Employees employee, Roles role) {
        Users user = toEntity(dto);
        user.setEmployee(employee);
        user.setRole(role);
        return user;
    }

    // List conversion
    public List<UserDTO> toDTOList(List<Users> users) {
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // ---------------- Additional Helpers ----------------

    // Get DTO by username
    public Optional<UserDTO> getUserDTOByUsername(String username) {
        return getUserByUsername(username).map(this::toDTO);
    }
}
