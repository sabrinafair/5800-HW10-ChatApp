package model;

import java.time.Instant;
import java.util.List;

public class Message {
//    sender, recipient(s), timestamp, and message content
    private User sender;
    private List<User> recipients;
    private String content;
    private Instant timestamp;

    public Message(User sender, List<User> recipients, String content) {
        this.content = content;
        this.recipients = recipients;
        this.sender = sender;
        this.timestamp = Instant.now();
    }

    public String getContent() {
        return content;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public User getSender() {
        return sender;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
