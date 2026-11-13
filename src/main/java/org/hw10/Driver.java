package org.hw10;


import model.User;
import services.ChatServer;

public class Driver {
    public static void main(String[] args) {
//        Users can send messages to one or more other users through the chat server.
        //Create server
        ChatServer chat = new ChatServer();

        //Create users
        User userA = new User("userA", chat);
        User userB = new User("userB", chat);
        User userC = new User("userC", chat);

        //Register users
        ChatServer.registerUser(userA);
        ChatServer.registerUser(userB);
        ChatServer.registerUser(userC);

        

//        Users can undo the last message they sent using the Memento design pattern.
//        Users can block messages from specific users using the Mediator design pattern.
//        Users can receive messages from other users and view the chat history for a specific user.
//        Your program should include a driver that demonstrates these features with 3 users.


    }
}