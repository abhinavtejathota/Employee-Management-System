package com.empapp.repository;

import com.empapp.model.Leaves;
import com.empapp.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeavesRepository extends JpaRepository<Leaves, Long> {

    List<Leaves> findByEmployee(Employees employee);

    List<Leaves> findByApprovedBy(Employees approvedBy);

    List<Leaves> findByStatus(Leaves.LeaveStatus status);
}
