package com.yet.spring.logger;

import com.yet.spring.Event;

import java.util.Collection;

public class CombinedEventLogger implements  EventLogger {

    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.forEach(logger -> logger.logEvent(event));
    }
}
