package com.app.web.marketing.application;

import com.app.web.marketing.domain.EventLog;
import com.app.web.marketing.infrastructure.IEventLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ActivityLoggerService implements IActivityLoggerService{

    @Autowired
    private IEventLogRepository eventLogRepository;

    public void log(EventLog eventLog) {
        eventLogRepository.save(eventLog);
    }
}
