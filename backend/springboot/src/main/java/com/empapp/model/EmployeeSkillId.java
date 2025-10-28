package com.empapp.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSkillId implements Serializable {

    private Long employeeId;
    private Long skillId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeSkillId)) return false;
        EmployeeSkillId that = (EmployeeSkillId) o;
        return Objects.equals(employeeId, that.employeeId) &&
               Objects.equals(skillId, that.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, skillId);
    }
}
