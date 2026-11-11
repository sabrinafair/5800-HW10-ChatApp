package services;

import model.Message;
import model.User;

import java.util.ArrayList;
import java.util.List;

//The mediator class
public class ChatServer {
    private static List<User> registeredUsers;
    private List<User> chatParticipants;

    private List<User> blockedUsers;
    private String chatName;
    private List<Message> conversation;

    public ChatServer(List<User> users) {
        this.blockedUsers = new ArrayList<>();
        this.chatParticipants = users;
        this.chatName = this.getAllUserNames();
        this.conversation = new ArrayList<>();
    }

    private String getAllUserNames() {
        String allUserNames = "";
        for(User user: registeredUsers)
    }

    public static void registerUser(User user) {
        if(!registeredUsers.contains(user)) registeredUsers.add(user);
    }

    public static void unregisterUser(User user) {

    }

    public void sendMessage(User from) {

    }

    public void blockUser(User user) {

    }
//    register and unregister users,
//    send messages from one user to one or more other users,
//    and block messages from specific users.


}
