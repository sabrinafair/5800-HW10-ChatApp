package services;

import java.time.Instant;

public class MessageMemento {

    private String messageContent;
    private Instant timestamp;

    public MessageMemento(String messageContent, Instant timestamp) {
        this.messageContent = messageContent;
        this.timestamp = timestamp;
    }

    public String getState() {
        return messageContent;
    }

    public Instant setState() {
        return timestamp;
    }
}