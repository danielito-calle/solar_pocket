package com.app.web.marketing.application;

import com.app.web.marketing.domain.EventLog;

public interface IActivityLoggerService  {
    public void log(EventLog eventLog);
}
