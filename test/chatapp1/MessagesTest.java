package chatapp1;

import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

public class MessagesTest {

    Messages key1;
    Path file = Path.of("Messages.json");
    byte[] oldData;
    boolean fileExists;

    @BeforeEach
    public void setUp() throws Exception {
        // save the original json file
        fileExists = Files.exists(file);
        if (fileExists) {
            oldData = Files.readAllBytes(file);
        }
        Files.writeString(file, "[]");
        // reset the arrays
        Messages.sentMessage = new String[10];
        Messages.disregardedMessage = new String[10];
        Messages.storedMessage = new String[10];
        Messages.messageHash = new String[10];
        Messages.messageID = new String[10];
        Messages.sentSender = new String[10];
        Messages.sentRecipient = new String[10];
        Messages.storedSender = new String[10];
        Messages.storedRecipient = new String[10];
        Messages.storedIDs = new String[10];
        Messages.storedHashes = new String[10];
        // reset the counters
        Messages.sentCount = 0;
        Messages.discardedCount = 0;
        Messages.storedCount = 0;
        Messages.MessagesSent = 0;
        Messages.currentMessageNumber = 0;
        Messages.Message = null;
        Messages.cellphone = null;
        Messages.UniqueMessageId = 0;
        Login.user = "kyl_1";
        key1 = new Messages();
    }

    @AfterEach
    public void tearDown() throws Exception {
        // restore the original json file
        if (fileExists) {
            Files.write(file, oldData);
        } else {
            Files.deleteIfExists(file);
        }
        Login.user = null;
    }

    // provide user input
    public void setInput(String text) {
        key1.scanner = new Scanner(text);
    }

    // capture printed messages
    public String getOutput(Runnable action) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try {
            System.setOut(new PrintStream(output));
            action.run();
        } finally {
            System.setOut(original);
        }
        return output.toString();
    }

    // prepare a message for testing
    public void prepareMessage(String text, long id) {
        Messages.Message = text;
        Messages.cellphone = "+27838968976";
        Messages.UniqueMessageId = id;
    }

    // test message length
    @Test
    public void testMessageLength() {
        assertTrue(key1.MessageLength("Hello World"));
        assertTrue(key1.MessageLength("A".repeat(250)));
        assertFalse(key1.MessageLength("A".repeat(251)));
        assertFalse(key1.MessageLength(null));
    }

    // test message id
    @Test
    public void testMessageID() {
        assertTrue(key1.CheckMessageID("1234567890"));
        assertFalse(key1.CheckMessageID("12345"));
        assertFalse(key1.CheckMessageID("abcdefghij"));
        long id = key1.GenerateMessageID();
        assertTrue(key1.CheckMessageID(String.valueOf(id)));
    }

    // test message hash
    @Test
    public void testMessageHash() {
        prepareMessage("Hello World", 1234567890L);
        Messages.currentMessageNumber = 0;
        assertEquals("12:0:HELLOWORLD", key1.GenerateMessageHash());
        String result = getOutput(() -> key1.MessageHash());
        assertEquals("12:0:HELLOWORLD", result.trim());
    }

    // test message validation
    @Test
    public void testMessageLengthValidator() {
        prepareMessage("Hello World", 1234567890L);
        String result = getOutput(() -> key1.MessageLengthValidator());
        assertTrue(result.contains("Message sent!"));
        assertTrue(result.contains("Hello World"));
        Messages.Message = "A".repeat(251);
        result = getOutput(() -> key1.MessageLengthValidator());
        assertTrue(result.contains("250 characters"));
        assertFalse(result.contains("Message sent!"));
    }

    // test recipient validation
    @Test
    public void testGetRecipient() {
        setInput("08966553\n+27838968976\n");
        String result = key1.GetRecipient();
        assertEquals("+27838968976", result);
    }

    // test message input
    @Test
    public void testGetMessage() {
        setInput("A".repeat(251) + "\nHello World\n");
        String result = key1.GetMessage();
        assertEquals("Hello World", result);
    }

    // test message options
    @Test
    public void testMessageOptions() {
        setInput("9\n1\n");
        int result = key1.MessageOptions();
        assertEquals(1, result);
    }

    // test sending a message
    @Test
    public void testSendMessage() {
        prepareMessage("Hello World", 1234567890L);
        key1.SendMessage();
        assertEquals(1, Messages.sentCount);
        assertEquals(1, Messages.MessagesSent);
        assertEquals("Hello World", Messages.sentMessage[0]);
        assertEquals("1234567890", Messages.messageID[0]);
    }

    // test disregarding a message
    @Test
    public void testDisregardMessage() {
        prepareMessage("Goodbye", 1234567890L);
        key1.DisregardMessage();
        assertEquals(1, Messages.discardedCount);
        assertEquals("Goodbye", Messages.disregardedMessage[0]);
        assertEquals(0, Messages.MessagesSent);
    }

    // test storing a message
    @Test
    public void testStoreMessage() throws Exception {
        prepareMessage("Hello World", 1234567890L);
        key1.StoreMessage();
        assertEquals(1, Messages.storedCount);
        assertEquals("Hello World", Messages.storedMessage[0]);
        String json = Files.readString(file);
        assertTrue(json.contains("Hello World"));
        assertTrue(json.contains("1234567890"));
    }

    // test reading messages from json
    @Test
    public void testReadJsonFile() {
        prepareMessage("First Message", 1234567890L);
        key1.StoreMessage();
        prepareMessage("Second Message", 1234567891L);
        key1.StoreMessage();
        Messages.storedCount = 0;
        key1.loadStoredMessages();
        assertEquals(2, Messages.storedCount);
        assertEquals("First Message", Messages.storedMessage[0]);
        assertEquals("Second Message", Messages.storedMessage[1]);
        String result = getOutput(() -> key1.ReadJsonFile());
        assertTrue(result.contains("First Message"));
        assertTrue(result.contains("Second Message"));
    }

    // test displaying senders and recipients
    @Test
    public void testDisplaySendersRecipients() {
        prepareMessage("Hello World", 1234567890L);
        key1.SendMessage();
        String result = getOutput(() -> key1.DisplaySendersRecipients());
        assertTrue(result.contains("kyl_1"));
        assertTrue(result.contains("+27838968976"));
    }

    // test finding the longest message
    @Test
    public void testLongestMessage() {
        prepareMessage("Hi", 1234567890L);
        key1.SendMessage();
        prepareMessage("Hello, how are you today?", 1234567891L);
        key1.SendMessage();
        String result = getOutput(() -> key1.DisplayLongestMessage());
        assertTrue(result.contains("Hello, how are you today?"));
    }

    // test searching by message id
    @Test
    public void testSearchMessageID() {
        prepareMessage("Hello World", 1234567890L);
        key1.SendMessage();
        setInput("1234567890\n");
        String result = getOutput(() -> key1.SearchMessageID());
        assertTrue(result.contains("Hello World"));
    }

    // test searching by recipient
    @Test
    public void testSearchRecipient() {
        prepareMessage("Hello World", 1234567890L);
        key1.SendMessage();
        setInput("+27838968976\n");
        String result = getOutput(() -> key1.SearchRecipient());
        assertTrue(result.contains("Hello World"));
    }

    // test deleting a sent message
    @Test
    public void testDeleteMessage() {
        prepareMessage("Hello World", 1234567890L);
        key1.SendMessage();
        setInput(Messages.messageHash[0] + "\n");
        key1.DeleteMessage();
        assertEquals(0, Messages.sentCount);
        assertNull(Messages.sentMessage[0]);
    }

    // test deleting a stored message
    @Test
    public void testDeleteStoredMessage() {
        prepareMessage("Hello World", 1234567890L);
        key1.StoreMessage();
        setInput(Messages.storedHashes[0] + "\n");
        key1.DeleteMessage();
        key1.loadStoredMessages();
        assertEquals(0, Messages.storedCount);
    }

    // test displaying the full report
    @Test
    public void testDisplayReport() {
        prepareMessage("Hello World", 1234567890L);
        key1.SendMessage();
        String result = getOutput(() -> key1.DisplayReport());
        assertTrue(result.contains("MESSAGE REPORT"));
        assertTrue(result.contains("Hello World"));
        assertTrue(result.contains("1234567890"));
    }

    // test the stored messages menu
    @Test
    public void testStoredMessages() {
        setInput("0\n");
        String result = getOutput(() -> key1.StoredMessages());
        assertTrue(result.contains("STORED MESSAGES"));
        assertTrue(result.contains("Display longest message"));
    }

    // test the main menu
    @Test
    public void testGreetingPage() {
        setInput("4\n");
        String result = getOutput(() -> key1.GreetingPage());
        assertTrue(result.contains("Welcome to QuickChat!"));
        assertTrue(result.contains("Thank you for using QuickChat"));
    }

    // test the complete message sending process
    @Test
    public void testSendMessages() {
        setInput("1\n+27838968976\nHello World\n1\n");
        key1.SendMessages();
        assertEquals(1, Messages.MessagesSent);
        assertEquals("Hello World", Messages.sentMessage[0]);
    }

    // test automatic array expansion
    @Test
    public void testArrayExpansion() {
        for (int i = 0; i < 11; i++) {
            prepareMessage("Message " + i, 1234567890L + i);
            key1.SendMessage();
        }
        assertEquals(11, Messages.sentCount);
        assertTrue(Messages.sentMessage.length >= 11);
    }
}