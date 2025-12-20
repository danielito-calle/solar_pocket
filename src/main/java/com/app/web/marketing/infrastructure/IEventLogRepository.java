package com.app.web.marketing.infrastructure;

import com.app.web.marketing.domain.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventLogRepository extends JpaRepository<EventLog,Long> {
}
