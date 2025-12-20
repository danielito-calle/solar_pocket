package com.app.web.administration.api.middlewares;

import com.app.web.marketing.application.IActivityLoggerService;
import com.app.web.marketing.domain.EventLog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
public class MetricsInterceptor implements HandlerInterceptor {

    @Autowired
    private IActivityLoggerService activityLoggerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            try {
                EventLog eventLog = new EventLog();
                eventLog.setCreatedAt(LocalDateTime.now());
                eventLog.setEventType("VISIT_PAGE");
                eventLog.setPage(request.getRequestURI());
                eventLog.setTarget(request.getRequestURI());

                String ipAddress = request.getHeader("X-Forwarded-For");
                if (ipAddress == null || ipAddress.isEmpty()) {
                    ipAddress = request.getRemoteAddr();
                }
                eventLog.setIpAddress(ipAddress);

                activityLoggerService.log(eventLog);
            } catch (Exception e) {
                System.err.println("Error al registrar métrica: " + e.getMessage());
            }
        }
        return true;
    }
}