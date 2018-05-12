package com.yet.spring;

public class App {

    private Client client;
    private ClientEventLogger eventLogger;

    public static void main(String[] args) {
        App app = new App();
        app.client = new Client("1", "John Smith");
        app.eventLogger = new ClientEventLogger();

        app.logEvent("Some event for user 1");
    }

    public void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getName());
        eventLogger.logEvent(message);
    }


}
