package com.empapp.repository;

import com.empapp.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {
	Optional<Employees> findById(Long employeeId);

    List<Employees> findByFirstName(String firstName);

    List<Employees> findByLastName(String lastName);
	
	List<Employees> findByManager(Employees manager);
}
