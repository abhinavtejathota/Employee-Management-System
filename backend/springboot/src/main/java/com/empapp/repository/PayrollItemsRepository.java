package com.empapp.repository;

import com.empapp.model.PayrollItems;
import com.empapp.model.Payroll;
import com.empapp.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollItemsRepository extends JpaRepository<PayrollItems, Long> {

    List<PayrollItems> findByPayroll(Payroll payroll);

    List<PayrollItems> findByEmployee(Employees employee);

    List<PayrollItems> findByPayrollAndPaidFalse(Payroll payroll);
}
