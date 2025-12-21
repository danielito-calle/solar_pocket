package com.app.web.administration.api.middlewares;

import com.app.web.marketing.application.IActivityLoggerService;
import com.app.web.marketing.domain.EventLog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class MetricsInterceptor implements HandlerInterceptor {

    @Autowired
    private IActivityLoggerService activityLoggerService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String method = request.getMethod();
        String uri = request.getRequestURI();

        if (isStaticResource(uri)) {
            return true;
        }

        if ("GET".equalsIgnoreCase(method) || "POST".equalsIgnoreCase(method)) {
            try {
                EventLog eventLog = new EventLog();
                eventLog.setCreatedAt(LocalDateTime.now());

                if ("POST".equalsIgnoreCase(method)) {
                    eventLog.setEventType("SEND_FORM");
                } else {
                    eventLog.setEventType("VISIT_PAGE");
                }

                eventLog.setPage(uri);
                eventLog.setTarget(uri);
                eventLog.setIpAddress(getClientIp(request));

                activityLoggerService.log(eventLog);

            } catch (Exception e) {
                System.err.println("Error al registrar métrica en " + uri + ": " + e.getMessage());
            }
        }
        return true;
    }

    private boolean isStaticResource(String uri) {
        return Arrays.asList(".css", ".js", ".png", ".jpg", ".jpeg", ".svg", ".ico", ".woff")
                .stream().anyMatch(uri::endsWith);
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Forwarded-For");
        if (remoteAddr == null || remoteAddr.isEmpty()) {
            remoteAddr = request.getRemoteAddr();
        }
        return remoteAddr;
    }
}