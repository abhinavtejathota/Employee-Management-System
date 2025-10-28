package com.empapp.repository;

import com.empapp.model.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DepartmentsRepository extends JpaRepository<Departments, Long> {
}
