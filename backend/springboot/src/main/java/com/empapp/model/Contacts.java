package com.empapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="contact_id")
    private Long contactId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable=false)
	private Employees employee;
	@Column(name="address_line1", length = 100)
	private String addressLine1;
	@Column(name="address_line2", length = 100)
	private String addressLine2;
	@Column(length = 50)
	private String city;
	@Column(length = 50)
	private String state;
	@Column(name="postal_code", length = 15)
	private String postalCode;
	@Column(name="email_official", length = 100)
	private String emailOfficial;
	@Column(name="email_personal", length = 100)
	private String emailPersonal;
	@Column(name="phone_mobile", length = 20)
	private String phoneMobile;
	@Column(name="emergency_contact_name", length = 50)
	private String emergencyContactName;
	@Column(name="emergency_contact_phone", length = 20)
	private String emergencyContactPhone;
	@Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;
	@Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;
}