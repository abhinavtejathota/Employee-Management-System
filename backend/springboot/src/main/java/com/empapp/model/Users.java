package com.empapp.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password_hash", length = 128)
    private String passwordHash;
	
	@Transient
	private String password;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Employees employee;  

    @Column(name = "full_name", length = 200)
    private String fullName;

    @Column(length = 150)
    private String email;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties("users")
	private Roles role;
}
