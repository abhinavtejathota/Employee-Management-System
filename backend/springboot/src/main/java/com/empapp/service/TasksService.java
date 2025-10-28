package com.empapp.service;

import com.empapp.dto.TasksDTO;
import com.empapp.model.Employees;
import com.empapp.model.Tasks;
import com.empapp.repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TasksRepository repository;

    // Create or update task
    public TasksDTO createTask(TasksDTO dto, Employees assignedTo, Employees assignedBy) {
        Tasks saved = repository.save(toEntity(dto, assignedTo, assignedBy));
        return toDTO(saved);
    }

    public List<TasksDTO> getAllTasks() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public TasksDTO getTaskById(Long taskId) {
        return repository.findById(taskId).map(this::toDTO).orElse(null);
    }

    public List<TasksDTO> getTasksByAssignee(Employees employee) {
        return repository.findByAssignedTo(employee).stream().map(this::toDTO).toList();
    }

    public List<TasksDTO> getTasksByAssigner(Employees employee) {
        return repository.findByAssignedBy(employee).stream().map(this::toDTO).toList();
    }

    public TasksDTO updateTask(Long taskId, TasksDTO dto, Employees assignedTo, Employees assignedBy) {
        return repository.findById(taskId).map(task -> {
            task.setTitle(dto.getTitle());
            task.setDescription(dto.getDescription());
            task.setStatus(dto.getStatus());
            task.setPriority(dto.getPriority());
            task.setDueDate(dto.getDueDate());
            task.setAssignedTo(assignedTo);
            task.setAssignedBy(assignedBy);
            return toDTO(repository.save(task));
        }).orElse(null);
    }

    public void deleteTask(Long taskId) {
        repository.deleteById(taskId);
    }

    // ---------------- Conversion ----------------
    public TasksDTO toDTO(Tasks task) {
        if (task == null) return null;

        TasksDTO dto = new TasksDTO();
        dto.setTaskId(task.getTaskId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());

        if (task.getAssignedTo() != null) {
            dto.setAssignedToId(task.getAssignedTo().getEmployeeId());
            dto.setAssignedToName(task.getAssignedTo().getFirstName() + " " + task.getAssignedTo().getLastName());
        }

        if (task.getAssignedBy() != null) {
            dto.setAssignedById(task.getAssignedBy().getEmployeeId());
            dto.setAssignedByName(task.getAssignedBy().getFirstName() + " " + task.getAssignedBy().getLastName());
        }
        return dto;
    }

    public Tasks toEntity(TasksDTO dto, Employees assignedTo, Employees assignedBy) {
        Tasks task = new Tasks();
        task.setTaskId(dto.getTaskId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
        task.setAssignedTo(assignedTo);
        task.setAssignedBy(assignedBy);
        return task;
    }
}
