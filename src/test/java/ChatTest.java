import config.Config;
import model.Message;
import model.User;
import org.junit.jupiter.api.Test;
import services.ChatHistory;
import services.ChatServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChatTest {
    ChatServer chat = new ChatServer();

    User userA = new User("userA", chat);
    User userB = new User("userB", chat);
    User userC = new User("userC", chat);

    ChatHistory history;

    @Test
    public void testSendingMessageToUnregisteredUser() {
        chat.registerUser(userA);
        userA.sendMessage(List.of(userB), Config.EXAMPLE_MESSAGE);

        assertFalse(hasMessage(userB, Config.EXAMPLE_MESSAGE), "Exepected messages to not be equal since user that was not registered should not have gotten message");
    }

    @Test
    public void testBlockedSendingMessageToUser() {
        chat.registerUser(userA);
        chat.registerUser(userB);
        userB.blockUser(userA);
        userA.sendMessage(List.of(userB), Config.EXAMPLE_MESSAGE);

        assertFalse(hasMessage(userB, Config.EXAMPLE_MESSAGE), "Exepected messages to not be equal since user was blocked");

    }

    @Test
    public void testSendingMessageToUser() {
        chat.registerUser(userA);
        chat.registerUser(userB);
        userA.sendMessage(List.of(userB), Config.EXAMPLE_MESSAGE);

        assertTrue(hasMessage(userB, Config.EXAMPLE_MESSAGE), "Exepected messages to be equal since user was registered and not blocked");

    }

    @Test
    public void testSendingMessageThenUnsend() {
        chat.registerUser(userA);
        chat.registerUser(userB);
        userA.sendMessage(List.of(userB), Config.EXAMPLE_MESSAGE);
        userA.undoLastMessage();

        assertFalse(hasMessage(userB, Config.EXAMPLE_MESSAGE), "Exepected messages to not be equal since user was unsent message");

    }

    @Test
    void testUnregisteredUserIsRemoved() {
        chat.registerUser(userA);
        chat.unregisterUser(userA);
        assertFalse(chat.getRegisteredUsers().contains(userA));
    }

    @Test
    void testMessageIsSentToMultipleRecipients() {
        chat.registerUser(userA);
        chat.registerUser(userB);
        chat.registerUser(userC);
        userA.sendMessage(List.of(userB, userC), Config.EXAMPLE_MESSAGE);

        assertTrue(hasMessage(userB, Config.EXAMPLE_MESSAGE), "Exepected messages to be equal since users were registered and not blocked");
        assertTrue(hasMessage(userC, Config.EXAMPLE_MESSAGE), "Exepected messages to be equal since users were registered and not blocked");

    }

    @Test
    void testBlockedRecipientDoesNotReceiveButOtherRecipientDoes() {
        chat.registerUser(userA);
        chat.registerUser(userB);
        chat.registerUser(userC);
        userC.blockUser(userA);
        userA.sendMessage(List.of(userB, userC), Config.EXAMPLE_MESSAGE);

        assertTrue(hasMessage(userB, Config.EXAMPLE_MESSAGE), "Exepected message to be equal since user was registered and not blocked");
        assertFalse(hasMessage(userC, Config.EXAMPLE_MESSAGE), "Exepected message to not be equal since user was blocked");

    }

    @Test
    void testUnblockedUserCanSendMessagesAgain() {
        chat.registerUser(userA);
        chat.registerUser(userB);
        userB.blockUser(userA);
        userA.sendMessage(List.of(userB), Config.EXAMPLE_MESSAGE);
        assertFalse(hasMessage(userB, Config.EXAMPLE_MESSAGE), "Exepected messages to not be equal since user was blocked");

        userB.unblockUser(userA);
        userA.sendMessage(List.of(userB), Config.EXAMPLE_MESSAGE);
        assertTrue(hasMessage(userB, Config.EXAMPLE_MESSAGE), "Exepected messages to be equal since user was registered and not blocked");

    }

    @Test
    void getLastMessageReturnsMostRecentMessage() {
        chat.registerUser(userA);
        chat.registerUser(userB);

        history = new ChatHistory();

        Message m1 = new Message(userA, List.of(userB), Config.EXAMPLE_MESSAGE);
        Message m2 = new Message(userA, List.of(userB), Config.EXAMPLE_MESSAGE);

        history.addMessage(m1);
        history.addMessage(m2);
        assertSame(m2, history.getLastMessage().orElseThrow(), "Exepected messages to be equal since history should return last message");

    }


    private boolean hasMessage(User user, String expectedContent) {
        for (Message message : user.getChatHistory()) {
            if (message.getContent().equals(expectedContent)) {
                return true;
            }
        }

        return false;
    }
}
