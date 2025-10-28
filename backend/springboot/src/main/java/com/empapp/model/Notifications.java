package com.empapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="notification_id")
    private Long notificationId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable=false)
	private Users user;
	@Column(name="message", length=500, nullable=false)
	private String message;
	@Column(name="link", length = 255)
	private String link;
	@Column(name = "is_read", columnDefinition = "tinyint(1) default 0")
	private Boolean isRead = false; 
	@Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;
}