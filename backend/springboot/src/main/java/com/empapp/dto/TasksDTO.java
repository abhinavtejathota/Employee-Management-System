package com.empapp.dto;

import java.sql.Timestamp;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.empapp.model.Tasks;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksDTO {
    private Long taskId;
    private String title;
    private String description;
    private Long assignedToId;
    private String assignedToName; 
    private Long assignedById;
    private String assignedByName; 
	@Enumerated(EnumType.STRING)
    private Tasks.TaskStatus status;
	@Enumerated(EnumType.STRING)
    private Tasks.TaskPriority priority;
    private Date dueDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
