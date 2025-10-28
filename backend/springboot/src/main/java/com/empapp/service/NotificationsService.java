package com.empapp.service;

import com.empapp.dto.NotificationsDTO;
import com.empapp.model.Notifications;
import com.empapp.model.Users;
import com.empapp.repository.NotificationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationsService {

    private final NotificationsRepository repository;

    // CREATE
    public Notifications createNotification(Notifications notification) {
        return repository.save(notification);
    }

    // READ
    public List<Notifications> getAllNotifications() {
        return repository.findAll();
    }

    public Notifications getNotificationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Notifications> getNotificationsByUser(Users user) {
        return repository.findByUser(user);
    }

    public List<Notifications> getUnreadNotificationsByUser(Users user) {
        return repository.findByUserAndIsReadFalse(user);
    }

    // UPDATE - Mark as read ✅
    public Notifications markAsRead(Long id) {
        return repository.findById(id).map(notification -> {
            notification.setIsRead(true);
            return repository.save(notification);
        }).orElse(null);
    }

    // DELETE
    public void deleteNotification(Long id) {
        repository.deleteById(id);
    }

    // ---------------- Conversion ----------------
    public NotificationsDTO toDTO(Notifications notification) {
        NotificationsDTO dto = new NotificationsDTO();
        dto.setNotificationId(notification.getNotificationId());
        dto.setUserId(notification.getUser() != null ? notification.getUser().getUserId() : null);
        dto.setUsername(notification.getUser() != null ? notification.getUser().getUsername() : null);
        dto.setMessage(notification.getMessage());
		dto.setLink(notification.getLink());
        dto.setIsRead(notification.getIsRead());
        dto.setCreatedAt(notification.getCreatedAt());
        return dto;
    }

    public Notifications toEntity(NotificationsDTO dto, Users user) {
        Notifications notification = new Notifications();
        notification.setNotificationId(dto.getNotificationId());
        notification.setUser(user);
        notification.setMessage(dto.getMessage());
		notification.setLink(dto.getLink());
        notification.setIsRead(dto.getIsRead());
        notification.setCreatedAt(dto.getCreatedAt());
        return notification;
    }

    // Convert list
    public List<NotificationsDTO> toDTOList(List<Notifications> notifications) {
        return notifications.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
