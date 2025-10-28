package com.empapp.controller;

import com.empapp.dto.UserRolesDTO;
import com.empapp.model.Roles;
import com.empapp.model.Users;
import com.empapp.service.UserRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserRolesController {

    private final UserRolesService service;

    @PostMapping("/assign")
    public UserRolesDTO assignRole(@RequestParam Long userId, @RequestParam Long roleId) {
        Users user = new Users();
        user.setUserId(userId);
        Roles role = new Roles();
        role.setRoleId(roleId);
        return service.assignRoleToUser(user, role);
    }

    @GetMapping("/users/{userId}")
    public List<UserRolesDTO> getRolesByUser(@PathVariable Long userId) {
        return service.getRolesByUser(userId);
    }

    @GetMapping("/roles/{roleId}")
    public List<UserRolesDTO> getUsersByRole(@PathVariable Long roleId) {
        return service.getUsersByRole(roleId);
    }

    @DeleteMapping("/remove")
    public void removeRole(@RequestParam Long userId, @RequestParam Long roleId) {
        service.removeRoleFromUser(userId, roleId);
    }
}
