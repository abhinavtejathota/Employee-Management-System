package com.empapp.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolesDTO {
    private Long roleId;
    private String roleName;
    private String description;
    private Set<Long> userIds; 
}
