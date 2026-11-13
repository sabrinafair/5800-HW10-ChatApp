package services;

import model.Message;
import model.User;

import java.util.*;

public class ChatHistory implements Iterable<Message> {
//    add a new message to the history and get the last message sent
    private List<Message> messages;

    public ChatHistory() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message){
        this.messages.add(message);
    }

    public void removeMessage(Message message){
        this.messages.remove(message);
    }

    public Optional<Message> getLastMessage() {
        return this.messages.stream()
                .max(Comparator.comparing(Message::getTimestamp));
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }
}
