package com.empapp.controller;

import com.empapp.dto.LoginResponseDTO;
import com.empapp.dto.SessionsDTO;
import com.empapp.dto.UserDTO;
import com.empapp.model.Users;
import com.empapp.service.AuditLogsService;
import com.empapp.service.SessionsService;
import com.empapp.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuthController {

    private final UsersService usersService;
    private final SessionsService sessionsService;
    private final AuditLogsService auditLogsService;

    // Password Hashing 
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        Optional<UserDTO> userDtoOpt = usersService.getUserDTOByUsername(username);
        if (userDtoOpt.isEmpty()) {
            return new LoginResponseDTO("error", "User not found", null, null, null);
        }

        UserDTO userDto = userDtoOpt.get();
        Users userEntity = usersService.getUserById(userDto.getUserId());

        if (!hashPassword(password).equals(userEntity.getPasswordHash())) {
            return new LoginResponseDTO("error", "Invalid credentials", null, null, null);
        }

        String redirectUrl = switch (userDto.getRoleName()) {
            case "ADMIN" -> "/admin/dashboard";
            case "MANAGER" -> "/manager/dashboard";
            case "EMPLOYEE" -> "/employee/dashboard";
            default -> "/dashboard";
        };

        SessionsDTO sessionDto = new SessionsDTO();
        sessionDto.setUserId(userEntity.getUserId());
        sessionDto.setUsername(userEntity.getUsername());
        sessionDto.setCreatedAt(Timestamp.from(Instant.now()));
        sessionDto.setExpiresAt(Timestamp.from(Instant.now().plusSeconds(300)));

        SessionsDTO savedSession = sessionsService.createSession(sessionDto, userEntity);

        auditLogsService.log(userEntity.getUserId(), "LOGIN", "Session",
                savedSession.getSessionId(), null); 

        return new LoginResponseDTO("success", "Login successful", userDto,
                redirectUrl, savedSession.getSessionId());
    }

    @PostMapping("/logout")
    public LoginResponseDTO logoutUser(@RequestHeader("X-Session-Id") Long sessionId) {
        SessionsDTO session = sessionsService.getSessionById(sessionId);
        if (session != null) {
            sessionsService.deleteSession(sessionId);
            auditLogsService.log(session.getUserId(), "LOGOUT", "Session", session.getSessionId(), null);
            return new LoginResponseDTO("success", "Logged out successfully!", null, "/login", null);
        } else {
            return new LoginResponseDTO("error", "Invalid session!", null, "/login", null);
        }
    }

    @PostMapping("/register")
    public LoginResponseDTO registerUser(@RequestBody Users user) {
        if (usersService.getUserByUsername(user.getUsername()).isPresent()) {
            return new LoginResponseDTO("error", "Username already exists!", null, null, null);
        }

        user.setPasswordHash(hashPassword(user.getPassword()));
        Users savedUser = usersService.saveUser(user);

        UserDTO dto = usersService.toDTO(savedUser);

        auditLogsService.log(savedUser.getUserId(), "CREATE", "Users",
                savedUser.getUserId(), null); 

        return new LoginResponseDTO("success", "User registered successfully!", dto, "/dashboard", null);
    }
}
