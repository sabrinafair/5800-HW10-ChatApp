package services;

import model.Message;
import model.User;

import java.util.*;

//The mediator class
//register and unregister users, send messages from one user to one or more other users
public class ChatServer {
    private List<User> registeredUsers = new ArrayList<>();
//    private User chatOwner;

    private Map<User, List<User>> blockedUsers  = new HashMap<>();
    private String chatName;

    public ChatServer() {
        this.chatName = this.getAllUserNames();
    }

    private String getAllUserNames() {
        String allUserNames = "";
        for(User user: this.registeredUsers){
            allUserNames += user.getName() + " ";
        }
        return allUserNames;
    }

    public void registerUser(User user) {

        if(!this.registeredUsers.contains(user)) this.registeredUsers.add(user);
    }

    public void unregisterUser(User user) {
        this.registeredUsers.remove(user);
    }

    public void sendMessage(User from, Message message) {
        List<User> userBlockedList;
        if(this.registeredUsers.contains(from)){
            for(User to: message.getRecipients()){
                userBlockedList = this.blockedUsers.getOrDefault(to, new ArrayList<>()); //check receiver list if from is blocked
                if(!userBlockedList.contains(from) && this.registeredUsers.contains(to)) {
                    to.receiveMessage(message);
                }
            }
        }
    }

    public void removeMessage(Message message) {
        for (User user : this.registeredUsers) {
            user.getChatHistory().removeMessage(message);
        }
    }

    public void blockUser(User user, User blockUser) {
        List<User> blockedList = this.blockedUsers.getOrDefault(user, new ArrayList<>());
        if(!blockedList.contains(blockUser)) blockedList.add(blockUser);
    }

    public void unblockUser(User user, User blockUser) {
        List<User> blockedList = this.blockedUsers.get(user);
        if(blockedList != null) blockedList.remove(blockUser);
    }

    public List<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public Map<User, List<User>> getBlockedUsers() {
        return this.blockedUsers;
    }

    public String getChatName() {
        return chatName;
    }

    public void setRegisteredUsers(List<User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public void setBlockedUsers(Map<User, List<User>> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

}
