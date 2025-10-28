package com.empapp.repository;

import com.empapp.model.Tasks;
import com.empapp.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> findByAssignedTo(Employees employee);

    List<Tasks> findByAssignedBy(Employees employee);

    List<Tasks> findByStatus(Tasks.TaskStatus status);

    List<Tasks> findByPriority(Tasks.TaskPriority priority);
}
