package com.empapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String fullName;
    private String email;
    private Long employeeId;  
    private Long roleId;      
    private String roleName;  
    private boolean enabled;
    private Timestamp createdAt;
	
	public UserDTO(Long userId, String username, String fullName, String email, String roleName) {
		this.userId = userId;
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.roleName = roleName;
	}
}
