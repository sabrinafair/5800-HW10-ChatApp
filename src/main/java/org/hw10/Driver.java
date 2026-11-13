package org.hw10;


import model.Message;
import model.User;
import services.ChatServer;

import java.util.List;

public class Driver {
    public static void main(String[] args) {
//        Users can send messages to one or more other users through the chat server.
        //Create server
        ChatServer chat = new ChatServer();

        //Create users
//        Your program should include a driver that demonstrates these features with 3 users.
        User userA = new User("userA", chat);
        User userB = new User("userB", chat);
        User userC = new User("userC", chat);

        //Register users
        chat.registerUser(userA);
        chat.registerUser(userB);
        chat.registerUser(userC);

        //Create example messages
        userA.sendMessage(List.of(userB, userC), "Hello everyone!");

//        Users can block messages from specific users using the Mediator design pattern.
        userB.blockUser(userA);
        userA.sendMessage(List.of(userB), "Can you see this?");

        for (Message message : userB.getChatHistory()) {
            System.out.println(message.getSender() + ": " + message.getContent());
        }

        for (Message message : userC.getChatHistory()) {
            System.out.println(message.getSender() + ": " + message.getContent());

        }
        //Undo last message from userA
        userA.undoLastMessage();
        userB.unblockUser(userA);

        //more example messages
        userA.sendMessage(List.of(userB), "Heyy guys wanna go to six flags??");
        userB.sendMessage(List.of(userB), "dudee the rides are so crazyy");
        userC.sendMessage(List.of(userB), "IM DOWN!! less goo");
//        Users can undo the last message they sent using the Memento design pattern.

        //iterate through messages
//        Users can receive messages from other users and view the chat history for a specific user.
        for (Message message : userA.getChatHistory()) {
            System.out.println(message.getSender() + ": " + message.getContent());
        }




    }
}