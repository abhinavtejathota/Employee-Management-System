package com.empapp.service;

import com.empapp.dto.AuditLogsDTO;
import com.empapp.model.AuditLogs;
import com.empapp.model.Users;
import com.empapp.repository.AuditLogsRepository;
import com.empapp.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditLogsService {

    private final AuditLogsRepository auditLogsRepository;
    private final UsersRepository usersRepository;
    private final ObjectMapper objectMapper = new ObjectMapper(); 

    // ------------------- Entity <-> DTO -------------------

    public AuditLogsDTO toDTO(AuditLogs log) {
        AuditLogsDTO dto = new AuditLogsDTO();
        dto.setAuditId(log.getAuditId());
        dto.setUserId(log.getUser() != null ? log.getUser().getUserId() : null);
        dto.setUsername(log.getUser() != null ? log.getUser().getUsername() : null);
        dto.setAction(log.getAction());
        dto.setEntity(log.getEntity());
        dto.setEntityId(log.getEntityId());
        dto.setDetails(log.getDetails());
        dto.setCreatedAt(log.getCreatedAt());
        return dto;
    }

    public AuditLogs toEntity(AuditLogsDTO dto, Users user) {
        AuditLogs log = new AuditLogs();
        log.setAuditId(dto.getAuditId());
        log.setUser(user);
        log.setAction(dto.getAction());
        log.setEntity(dto.getEntity());
        log.setEntityId(dto.getEntityId());
        log.setDetails(dto.getDetails());
        return log;
    }

    // ------------------- CREATE -------------------

    public AuditLogsDTO createAuditLog(AuditLogsDTO dto) {
        Users user = null;
        if (dto.getUserId() != null) {
            user = usersRepository.findById(dto.getUserId()).orElse(null);
        }
        AuditLogs saved = auditLogsRepository.save(toEntity(dto, user));
        return toDTO(saved);
    }

    // ------------------- READ -------------------

    public List<AuditLogsDTO> getAllAuditLogs() {
        return auditLogsRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AuditLogsDTO getAuditLogById(Long id) {
        return auditLogsRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public List<AuditLogsDTO> getAuditLogsByUserId(Long userId) {
        Users user = usersRepository.findById(userId).orElse(null);
        if (user == null) return List.of();
        return auditLogsRepository.findByUser(user).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<AuditLogsDTO> getAuditLogsByEntity(String entity) {
        return auditLogsRepository.findByEntity(entity).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ------------------- DELETE -------------------

    public void deleteAuditLog(Long id) {
        auditLogsRepository.deleteById(id);
    }

    // ------------------- LOG ACTION -------------------
	public void log(Long userId, String action, String entity, Long entityId, Map<String, Object> details) {
		Users user = null;
		if (userId != null) {
			user = usersRepository.findById(userId).orElse(null);
		}
	
		AuditLogs log = new AuditLogs();
		log.setUser(user);
		log.setAction(action);
		log.setEntity(entity);
	
		log.setEntityId(entityId != null ? String.valueOf(entityId) : null);
	
		if (details != null) {
			try {
				String jsonDetails = objectMapper.writeValueAsString(details);
				log.setDetails(jsonDetails);
			} catch (Exception e) {
				log.setDetails("{}"); 
			}
		} else {
			log.setDetails("{}");
		}
	
		auditLogsRepository.save(log);
	}
}
