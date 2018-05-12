package com.yet.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        app.logEvent((Event) ctx.getBean("event"), "Some event for user 1");
        app.logEvent((Event) ctx.getBean("event"), "Some event for user 2");
    }

    private void logEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getName());
        event.setMsg(message);

        logEvent(event);
    }

    private void logEvent(Event event) {
        eventLogger.logEvent(event);
    }


}
