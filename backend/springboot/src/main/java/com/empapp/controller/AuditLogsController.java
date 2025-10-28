package com.empapp.controller;

import com.empapp.dto.AuditLogsDTO;
import com.empapp.service.AuditLogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuditLogsController {

    private final AuditLogsService auditLogsService;

    @PostMapping
    public ResponseEntity<AuditLogsDTO> createAuditLog(@RequestBody AuditLogsDTO dto) {
        AuditLogsDTO saved = auditLogsService.createAuditLog(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<AuditLogsDTO> getAllAuditLogs() {
        return auditLogsService.getAllAuditLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLogsDTO> getAuditLogById(@PathVariable Long id) {
        AuditLogsDTO log = auditLogsService.getAuditLogById(id);
        return (log != null) ? ResponseEntity.ok(log) : ResponseEntity.notFound().build();
    }

    @GetMapping("/users/{userId}")
    public List<AuditLogsDTO> getAuditLogsByUser(@PathVariable Long userId) {
        return auditLogsService.getAuditLogsByUserId(userId);
    }

    @GetMapping("/entity/{entity}")
    public List<AuditLogsDTO> getAuditLogsByEntity(@PathVariable String entity) {
        return auditLogsService.getAuditLogsByEntity(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuditLog(@PathVariable Long id) {
        auditLogsService.deleteAuditLog(id);
        return ResponseEntity.noContent().build();
    }
}
