package com.empapp.controller;

import com.empapp.dto.RolesDTO;
import com.empapp.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class RolesController {

    private final RolesService service;

    @PostMapping
    public RolesDTO createRole(@RequestBody RolesDTO dto) {
        return service.createRole(dto);
    }

    @GetMapping
    public List<RolesDTO> getAllRoles() {
        return service.getAllRoles();
    }

    @GetMapping("/{id}")
    public RolesDTO getRoleById(@PathVariable Long id) {
        return service.getRoleById(id);
    }

    @GetMapping("/name/{roleName}")
    public RolesDTO getRoleByName(@PathVariable String roleName) {
        return service.getRoleByName(roleName);
    }

    @PutMapping("/{id}")
    public RolesDTO updateRole(@PathVariable Long id, @RequestBody RolesDTO dto) {
        return service.updateRole(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        service.deleteRole(id);
    }
}
