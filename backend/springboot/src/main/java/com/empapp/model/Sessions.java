package com.empapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private Users user;
	@Column(name="refresh_token_hash",length=255)
	private String refreshTokenHash;
	@Column(name="created_at", insertable = false, updatable = false)
	private Timestamp createdAt;
	@Column(name="expires_at")
	private Timestamp expiresAt;
}