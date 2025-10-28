package com.empapp.repository;

import com.empapp.model.EmployeeSkills;
import com.empapp.model.EmployeeSkillId;
import com.empapp.model.Employees;
import com.empapp.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeSkillsRepository extends JpaRepository<EmployeeSkills, EmployeeSkillId> {

    List<EmployeeSkills> findByEmployee(Employees employee);

    List<EmployeeSkills> findBySkill(Skills skill);
}
