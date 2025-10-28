package com.empapp.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionsDTO {
    private Long sessionId;
    private Long userId;
    private String username; 
    private String refreshTokenHash;
    private Timestamp createdAt;
    private Timestamp expiresAt;
}
