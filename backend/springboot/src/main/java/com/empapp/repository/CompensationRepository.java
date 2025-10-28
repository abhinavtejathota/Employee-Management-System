package com.empapp.repository;

import com.empapp.model.Compensation;
import com.empapp.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompensationRepository extends JpaRepository<Compensation, Long> {
    List<Compensation> findByEmployee(Employees employee);
}
