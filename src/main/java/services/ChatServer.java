package services;

import model.Message;
import model.User;

import java.util.*;

//The mediator class
//register and unregister users, send messages from one user to one or more other users
public class ChatServer {
    private static List<User> registeredUsers = new ArrayList<>();
//    private User chatOwner;

    private static Map<User, List<User>> blockedUsers  = new HashMap<>();
    private String chatName;
    private List<Message> conversation;

    public ChatServer() {
        this.chatName = this.getAllUserNames();
        this.conversation = new ArrayList<>();
    }

    private String getAllUserNames() {
        String allUserNames = "";
        for(User user: registeredUsers){
            allUserNames += user.getName() + " ";
        }
        return allUserNames;
    }

    public static void registerUser(User user) {

        if(!registeredUsers.contains(user)) registeredUsers.add(user);
    }

    public static void unregisterUser(User user) {
        registeredUsers.remove(user);
    }

    public void sendMessage(User from, Message message) {
        List<User> userBlockedList;
        if(registeredUsers.contains(from)){
            for(User to: message.getRecipients()){
                userBlockedList = blockedUsers.getOrDefault(to, Collections.emptyList()); //check receiver list if from is blocked
                if(!userBlockedList.contains(from) && registeredUsers.contains(to)) {
                    to.receiveMessage(message);
                }
            }
        }
    }

    public void removeMessage(Message message) {
        this.conversation.remove(message);
    }

    public void blockUser(User user, User blockUser) {
        List<User> blockedList = blockedUsers.getOrDefault(user, Collections.emptyList());
        if(!blockedList.contains(blockUser)) blockedList.add(blockUser);
    }

    public void unblockUser(User user, User blockUser) {
        List<User> blockedList = blockedUsers.getOrDefault(user, Collections.emptyList());
        blockedList.remove(blockUser);
    }

    public static List<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public static Map<User, List<User>> getBlockedUsers() {
        return blockedUsers;
    }

    public String getChatName() {
        return chatName;
    }

    public List<Message> getConversation() {
        return conversation;
    }

    public static void setRegisteredUsers(List<User> registeredUsers) {
        ChatServer.registeredUsers = registeredUsers;
    }

    public static void setBlockedUsers(Map<User, List<User>> blockedUsers) {
        ChatServer.blockedUsers = blockedUsers;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public void setConversation(List<Message> conversation) {
        this.conversation = conversation;
    }
}
