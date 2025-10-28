package com.empapp.controller;

import com.empapp.dto.TasksDTO;
import com.empapp.model.Employees;
import com.empapp.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class TasksController {

    private final TasksService service;

    @PostMapping
    public TasksDTO createTask(@RequestBody TasksDTO dto) {
        Employees assignedTo = new Employees();
        assignedTo.setEmployeeId(dto.getAssignedToId());
        Employees assignedBy = new Employees();
        assignedBy.setEmployeeId(dto.getAssignedById());

        return service.createTask(dto, assignedTo, assignedBy);
    }

    @GetMapping
    public List<TasksDTO> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public TasksDTO getTaskById(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @GetMapping("/assigned-to/{employeeId}")
    public List<TasksDTO> getTasksByAssignee(@PathVariable Long employeeId) {
        Employees employee = new Employees();
        employee.setEmployeeId(employeeId);
        return service.getTasksByAssignee(employee);
    }

    @GetMapping("/assigned-by/{employeeId}")
    public List<TasksDTO> getTasksByAssigner(@PathVariable Long employeeId) {
        Employees employee = new Employees();
        employee.setEmployeeId(employeeId);
        return service.getTasksByAssigner(employee);
    }

    @PutMapping("/{id}")
    public TasksDTO updateTask(@PathVariable Long id, @RequestBody TasksDTO dto) {
        Employees assignedTo = new Employees();
        assignedTo.setEmployeeId(dto.getAssignedToId());
        Employees assignedBy = new Employees();
        assignedBy.setEmployeeId(dto.getAssignedById());

        return service.updateTask(id, dto, assignedTo, assignedBy);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }
}
