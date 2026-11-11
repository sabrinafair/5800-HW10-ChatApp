package model;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private String chatName;
    private List<Message> conversation;

    public ChatRoom(List<User> users) {
        this.chatName =
        this.conversation = new ArrayList<>();
    }

    public void addMessage(Message message) {
        this.conversation.add(message);
    }

    public void removeMessage(Message message) {
        this.conversation.remove(message);
    }

    @Override
    public String toString() {
        for(Message)
        return "ChatRoom{" +
                "conversation=" + conversation +
                '}';
    }
}
