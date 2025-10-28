package com.empapp.repository;

import com.empapp.model.Users;
import com.empapp.dto.UserDTO;
import com.empapp.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByUsername(String username);
	
	@Query("SELECT new com.empapp.dto.UserDTO(u.userId, u.username, u.fullName, u.email, " +
           "CASE WHEN u.role IS NOT NULL THEN u.role.roleName ELSE 'UNKNOWN' END) " +
           "FROM Users u WHERE u.username = :username")
	Optional<UserDTO> findUserDTOByUsername(@Param("username") String username);
    List<Users> findByEmployee(Employees employee);
}
