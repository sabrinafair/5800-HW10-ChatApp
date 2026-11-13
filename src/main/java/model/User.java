package model;

import services.ChatHistory;
import services.ChatServer;
import services.MessageMemento;

import java.util.List;

public class User {
//    methods to send messages to other users,
//    receive messages from other users,
//    and undo the last message sent
    private String name;
    private ChatHistory chatHistory;
    private ChatServer chatServer;

    private MessageMemento lastMessageMemento;

    public User(String name, ChatServer chatServer) {
        this.name = name;
        this.chatHistory = new ChatHistory();
        this.chatServer = chatServer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatHistory getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ChatHistory chatHistory) {
        this.chatHistory = chatHistory;
    }

    public ChatServer getChatServer() {
        return chatServer;
    }

    public void setChatServer(ChatServer chatServer) {
        this.chatServer = chatServer;
    }

    public void blockUser(User user){
        chatServer.blockUser(this, user);
    }

    public void unblockUser(User user){
        chatServer.unblockUser(this, user);
    }

    public void sendMessage(List<User> to, String content){
        Message message = new Message(this, to, content);

        this.lastMessageMemento = new MessageMemento(message, message.getTimestamp());
        this.chatHistory.addMessage(message);

        this.chatServer.sendMessage(this, message);

    }

    public void receiveMessage(Message message){
        this.chatHistory.addMessage(message);
    }

    public void undoLastMessage(){
        if(this.lastMessageMemento != null){
            this.chatServer.removeMessage(this.lastMessageMemento.getState());
            this.lastMessageMemento = null;
        }
    }


}
