package com.app.web.marketing.application;

import com.app.web.marketing.domain.EventLog;
import com.app.web.marketing.infrastructure.IEventLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ActivityLoggerService implements IActivityLoggerService {

    @Autowired
    private IEventLogRepository repository;


    @Value("${solar.metrics.batch-size:5}")
    private int batchSize;

    private final List<EventLog> buffer = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void log(EventLog event) {
        buffer.add(event);

        if (buffer.size() >= batchSize) {
            flush();
        }
    }

    private synchronized void flush() {
        if (!buffer.isEmpty()) {
            try {
                repository.saveAll(buffer);
                buffer.clear();
            } catch (Exception e) {
            }
        }
    }

    @Scheduled(fixedDelayString = "${solar.metrics.flush-rate:900000}")
    public void forceFlush() {
        if (!buffer.isEmpty()) {
            flush();
        }
    }
}