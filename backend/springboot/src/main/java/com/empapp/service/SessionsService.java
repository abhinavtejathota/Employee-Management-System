package com.empapp.service;

import com.empapp.model.Sessions;
import com.empapp.model.Users;
import com.empapp.dto.SessionsDTO;
import com.empapp.repository.SessionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionsService {

    private final SessionsRepository repository;

    // Convert entity → DTO
    public SessionsDTO toDTO(Sessions session) {
        SessionsDTO dto = new SessionsDTO();
        dto.setSessionId(session.getSessionId()); // Long now
        dto.setUserId(session.getUser() != null ? session.getUser().getUserId() : null);
        dto.setUsername(session.getUser() != null ? session.getUser().getUsername() : null);
        dto.setCreatedAt(session.getCreatedAt());
        dto.setExpiresAt(session.getExpiresAt());
        return dto;
    }

    // Convert DTO → entity
    public Sessions toEntity(SessionsDTO dto, Users user) {
        Sessions session = new Sessions();
        session.setUser(user);
        session.setCreatedAt(dto.getCreatedAt());
        session.setExpiresAt(dto.getExpiresAt());
        return session;
    }

    // Create session
    public SessionsDTO createSession(SessionsDTO dto, Users user) {
        Sessions saved = repository.save(toEntity(dto, user));
        return toDTO(saved);
    }

    // Get session by ID
    public SessionsDTO getSessionById(Long sessionId) {
        return repository.findById(sessionId).map(this::toDTO).orElse(null);
    }

    // Delete session by ID
    public void deleteSession(Long sessionId) {
        repository.deleteById(sessionId);
    }

    // Get all sessions for a user
    public List<SessionsDTO> getSessionsByUser(Users user) {
        return repository.findByUser(user).stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Delete all sessions for a user
    public void deleteSessionsByUser(Users user) {
        repository.deleteByUser(user);
    }
}
