package com.empapp.aop;

import com.empapp.service.AuditLogsService;
import com.empapp.service.SessionsService;
import com.empapp.model.Sessions;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogsService auditService;
    private final SessionsService sessionsService;
    private final HttpServletRequest request;

    private static final String[] ENTITY_ID_KEYS = {
            "id", "userId", "employeeId", "departmentId", "roleId", "sessionId"
    };

    @AfterReturning("execution(public * com.empapp.controller..*(..))")
    public void logControllerAction(JoinPoint joinPoint) {
        try {
            String className = joinPoint.getTarget().getClass().getSimpleName();
            if (className.equals("AuthController")) {
                String methodName = joinPoint.getSignature().getName().toLowerCase();
                if (methodName.contains("login") || methodName.contains("logout")) {
                    return;
                }
            }

            String sessionIdHeader = request.getHeader("X-Session-Id");
            Sessions session = null;
            if (sessionIdHeader != null) {
                try {
                    Long sessionId = Long.valueOf(sessionIdHeader);
                    var dto = sessionsService.getSessionById(sessionId);
                    if (dto != null) session = sessionsService.toEntity(dto, null);
                } catch (NumberFormatException ignored) {}
            }
            Long userId = (session != null && session.getUser() != null) ? session.getUser().getUserId() : null;

            String entity = className.replace("Controller", "");
            String methodName = joinPoint.getSignature().getName().toLowerCase();

            String action;
            if (methodName.contains("register") || methodName.contains("create")) action = "CREATE";
            else if (methodName.contains("update")) action = "UPDATE";
            else if (methodName.contains("delete") || methodName.contains("remove")) action = "DELETE";
            else action = methodName.toUpperCase();

            Map<String, Object> details = new HashMap<>();
            Long entityId = null;

            for (Object arg : joinPoint.getArgs()) {
                details.put(arg.getClass().getSimpleName(), arg);

                if (arg instanceof Map<?, ?> mapArg) {
                    for (String key : ENTITY_ID_KEYS) {
                        if (mapArg.containsKey(key)) {
                            Object val = mapArg.get(key);
                            if (val != null) {
                                try {
                                    entityId = Long.valueOf(val.toString());
                                } catch (NumberFormatException ignored) {}
                                break;
                            }
                        }
                    }
                } else {
                    for (String key : ENTITY_ID_KEYS) {
                        try {
                            Field field = arg.getClass().getDeclaredField(key);
                            field.setAccessible(true);
                            Object value = field.get(arg);
                            if (value != null) {
                                try {
                                    entityId = Long.valueOf(value.toString());
                                } catch (NumberFormatException ignored) {}
                                break;
                            }
                        } catch (NoSuchFieldException | IllegalAccessException ignored) {}
                    }
                }
                if (entityId != null) break;
            }

            auditService.log(userId, action, entity, entityId, details);

        } catch (Exception e) {
            System.err.println("Audit logging failed: " + e.getMessage());
        }
    }
}
