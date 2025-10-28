package com.empapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesDTO {
    private Long userId;
    private String username; 
    private Long roleId;
    private String roleName; 
}
