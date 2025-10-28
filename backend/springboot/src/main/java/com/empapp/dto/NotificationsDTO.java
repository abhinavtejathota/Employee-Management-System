package com.empapp.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationsDTO {
    private Long notificationId;
    private Long userId;
    private String username; 
    private String message;
    private String link;
    private Boolean isRead;
    private Timestamp createdAt;
}
