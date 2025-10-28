package com.empapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="skill_id")
    private Long skillId;
	@Column(name="skill_name", nullable=false, unique=true, length=50)
	private String skillName;
}