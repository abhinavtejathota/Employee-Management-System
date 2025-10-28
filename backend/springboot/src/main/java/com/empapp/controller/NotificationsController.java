package com.empapp.controller;

import com.empapp.dto.NotificationsDTO;
import com.empapp.model.Users;
import com.empapp.model.Notifications;
import com.empapp.service.NotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class NotificationsController {

    private final NotificationsService service;

    @PostMapping
    public NotificationsDTO createNotification(@RequestBody NotificationsDTO dto) {
        Users user = new Users();
        user.setUserId(dto.getUserId());
        Notifications saved = service.createNotification(service.toEntity(dto, user));
        return service.toDTO(saved);
    }

    @GetMapping
    public List<NotificationsDTO> getAllNotifications() {
        return service.toDTOList(service.getAllNotifications());
    }

    @GetMapping("/{id}")
    public NotificationsDTO getNotificationById(@PathVariable Long id) {
        Notifications n = service.getNotificationById(id);
        return n != null ? service.toDTO(n) : null;
    }

    @GetMapping("/users/{userId}")
    public List<NotificationsDTO> getNotificationsByUser(@PathVariable Long userId) {
        Users user = new Users();
        user.setUserId(userId);
        return service.toDTOList(service.getNotificationsByUser(user));
    }

    @GetMapping("/users/{userId}/unread")
    public List<NotificationsDTO> getUnreadNotifications(@PathVariable Long userId) {
        Users user = new Users();
        user.setUserId(userId);
        return service.toDTOList(service.getUnreadNotificationsByUser(user));
    }

    @PutMapping("/mark-read/{id}")
    public NotificationsDTO markAsRead(@PathVariable Long id) {
        Notifications updated = service.markAsRead(id);
        return updated != null ? service.toDTO(updated) : null;
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        service.deleteNotification(id);
    }
}
