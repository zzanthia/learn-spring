package com.yet.spring;

import com.yet.spring.logger.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.Map;

public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> eventLoggers;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> eventLoggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.eventLoggers = eventLoggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        app.logEvent((Event) ctx.getBean("event"), EventType.INFO, "Some event for user 1");
        app.logEvent((Event) ctx.getBean("event"), EventType.ERROR, "Some event for user 2");
        app.logEvent((Event) ctx.getBean("event"), null, "Some event for user 3");
        app.logEvent((Event) ctx.getBean("event"), null, "Some event for user 4");
        ctx.close();
    }

    private void logEvent(Event event, EventType eventType, String msg) {
        String message = msg.replaceAll(client.getId(), client.getName() + "(" + client.getGreeting() + ")");
        event.setMsg(message);

        logEvent(event, eventType);
    }

    private void logEvent(Event event, EventType eventType) {
        EventLogger eventLogger = eventLoggers.get(eventType);
        if (eventLogger == null) {
            eventLogger = defaultLogger;
        }
        eventLogger.logEvent(event);
    }


}
