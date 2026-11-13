package services;

import model.Message;

import java.time.Instant;

public class MessageMemento {

    private Message message;
    private Instant timestamp;

    public MessageMemento(Message message, Instant timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public Message getState() {
        return this.message;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }
}