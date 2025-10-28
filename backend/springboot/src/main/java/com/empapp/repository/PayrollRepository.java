package com.empapp.repository;

import com.empapp.model.Payroll;
import com.empapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    List<Payroll> findByCreatedBy(Users user);

    List<Payroll> findByStatus(Payroll.PayrollStatus status);
}
