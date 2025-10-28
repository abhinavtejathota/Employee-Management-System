//AuditLogs -> Auditrepo, AuditService, AuditControl
package com.empapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "audit_logs") //Maps to table "audit_logs" in the database
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private Long auditId; //Primary key of audit_logs table

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //Maps to the "user_id" foreign key referencing the Users table
    private Users user; //Used for filtering logs by user (in repository & service)

    @Column(nullable = false, length = 255)
    private String action; //Stores the performed action 

    @Column(length = 100)
    private String entity; //Represents the entity/table involved in the action 

    @Column(name = "entity_id", length = 100)
    private String entityId; //ID of the entity affected 

	//storing as text can be converted later if needed
    @Column(columnDefinition = "json")
    private String details; //Stores detailed JSON data about the operation

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt; //Automatically populated timestamp when log entry is created (by DB trigger or default)
}
