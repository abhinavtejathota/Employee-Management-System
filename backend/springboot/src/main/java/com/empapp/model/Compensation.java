package com.empapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.sql.Timestamp;

@Entity
@Table(name = "compensation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compensation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="comp_id")
    private Long compId;
	@ManyToOne(fetch  = FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable = false)
	private Employees employee;
	@Column(precision = 12, scale = 2)
    private BigDecimal salary;
	@Column(name="pay_frequency", length = 20)
    private String payFrequency;
    @Column(name="back_account", length = 30)
	private String bankAccount;
    @Column(name="tax_id", length = 20)
	private String taxId;
	@Column(name="created_at", insertable = false, updatable = false)
	private Timestamp createdAt;
	@Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;
}