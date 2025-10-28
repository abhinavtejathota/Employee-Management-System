package com.empapp.repository;

import com.empapp.model.UserRoles;
import com.empapp.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles, UserRoleId> {

    List<UserRoles> findByUserUserId(Long userId);

    List<UserRoles> findByRoleRoleId(Long roleId);
}
