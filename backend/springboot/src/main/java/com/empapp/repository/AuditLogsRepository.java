//AuditLogs, Users -> Auditrepo -> AuditService
package com.empapp.repository;

import com.empapp.model.AuditLogs;
import com.empapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuditLogsRepository extends JpaRepository<AuditLogs, Long> {
	// Inherits built-in CRUD methods like save(), findAll(), findById(), deleteById()
    List<AuditLogs> findByUser(Users user); //Custom finder: fetch all logs linked to a specific user (via user_id foreign key
    List<AuditLogs> findByEntity(String entity); //Custom finder: fetch all logs related to a particular entity type
}
