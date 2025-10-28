package com.empapp.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogsDTO {
    private Long auditId;
    private Long userId;
    private String username; 
    private String action;
    private String entity;
    private String entityId;
    private String details; 
    private Timestamp createdAt;
}
