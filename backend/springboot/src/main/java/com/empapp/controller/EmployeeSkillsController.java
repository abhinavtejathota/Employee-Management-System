package com.empapp.controller;

import com.empapp.dto.EmployeeSkillsDTO;
import com.empapp.model.Employees;
import com.empapp.model.Skills;
import com.empapp.service.EmployeeSkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-skills")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class EmployeeSkillsController {

    private final EmployeeSkillsService service;

    // POST /api/employee-skills
    @PostMapping
    public EmployeeSkillsDTO addSkill(@RequestBody EmployeeSkillsDTO dto) {
        Employees employee = new Employees();
        employee.setEmployeeId(dto.getEmployeeId());

        Skills skill = new Skills();
        skill.setSkillId(dto.getSkillId());

        return service.addSkillToEmployee(dto, employee, skill);
    }

    // GET /api/employee-skills/employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public List<EmployeeSkillsDTO> getSkillsByEmployee(@PathVariable Long employeeId) {
        Employees employee = new Employees();
        employee.setEmployeeId(employeeId);
        return service.getSkillsByEmployee(employee);
    }

    // DELETE /api/employee-skills?employeeId=1&skillId=2
    @DeleteMapping
    public void removeSkill(@RequestParam Long employeeId, @RequestParam Long skillId) {
        service.removeSkillFromEmployee(employeeId, skillId);
    }

    // GET /api/employee-skills
    @GetMapping
    public List<EmployeeSkillsDTO> getAllEmployeeSkills() {
        return service.getAllEmployeeSkills();
    }
}
