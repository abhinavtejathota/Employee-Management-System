//entity
package com.empapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSkills {
    @EmbeddedId
    private EmployeeSkillId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employees employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private Skills skill;
}