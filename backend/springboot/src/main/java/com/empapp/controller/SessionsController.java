package com.empapp.controller;

import com.empapp.dto.SessionsDTO;
import com.empapp.model.Users;
import com.empapp.repository.UsersRepository;
import com.empapp.service.SessionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class SessionsController {

    private final SessionsService service;
    private final UsersRepository userRepository;

    @PostMapping
    public SessionsDTO createSession(@RequestBody SessionsDTO dto) {
        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return service.createSession(dto, user);
    }

    @GetMapping("/{id}")
    public SessionsDTO getSessionById(@PathVariable Long id) {
        return service.getSessionById(id);
    }

    @GetMapping("/users/{userId}")
    public List<SessionsDTO> getSessionsByUser(@PathVariable Long userId) {
        Users user = new Users();
        user.setUserId(userId);
        return service.getSessionsByUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteSession(@PathVariable Long id) {
        service.deleteSession(id);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteSessionsByUser(@PathVariable Long userId) {
        Users user = new Users();
        user.setUserId(userId);
        service.deleteSessionsByUser(user);
    }
}
