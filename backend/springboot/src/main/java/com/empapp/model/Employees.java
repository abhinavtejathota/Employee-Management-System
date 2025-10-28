package com.empapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
    private Long employeeId;
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	@Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	private String gender;
	private String nationality;
	@Column(name = "marital_status")
	private String maritalStatus;
	@Column(name = "hire_date")
	private LocalDate hireDate;
	@Column(name = "employment_type")
	private String employmentType;
	@Column(name = "job_title")
	private String jobTitle;
	@ManyToOne
    @JoinColumn(name = "department_id")
	private Departments department;
	@ManyToOne
    @JoinColumn(name = "manager_id")
	private Employees manager;
	@OneToMany(mappedBy = "manager")
	private Set<Employees> subordinates = new HashSet<>();
	private String status;
	@Column(name = "created_at", updatable = false, insertable = false)
    private Timestamp createdAt;
    @Column(name = "updated_at", insertable = false)
    private Timestamp updatedAt;
}
