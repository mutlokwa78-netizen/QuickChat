
package chatapp1;

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;  
import java.io.IOException;
import java.io.File;

public class Messages {

    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    Login key1 = new Login();

    // Arrays
    static String[] sentMessage = new String[10];
    static String[] disregardedMessage = new String[10];
    static String[] storedMessage = new String[10];
    static String[] messageHash = new String[10];
    static String[] messageID = new String[10];

    static String[] sentSender = new String[10];
    static String[] sentRecipient = new String[10];

    static String[] storedSender = new String[10];
    static String[] storedRecipient = new String[10];
    static String[] storedIDs = new String[10];
    static String[] storedHashes = new String[10];

    static long UniqueMessageId;
    static String cellphone;
    static String Message;

    static int MessagesSent = 0;
    static int sentCount = 0;
    static int discardedCount = 0;
    static int storedCount = 0;
    static int currentMessageNumber = 0;

    public Messages() {
        loadStoredMessages();
    }

    // The Chat App's Main Menu
    public void GreetingPage() {

        int choice;
        do {
            System.out.println("\nWelcome to QuickChat!");
            System.out.println("Please choose from the following options");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Stored Messages");
            System.out.println("4. Quit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> SendMessages();
                case 2 -> ShowSentMessages();
                case 3 -> StoredMessages();
                case 4 -> System.out.println("Thank you for using QuickChat");
                default -> System.out.println("Invalid option!");
            }

        } while (choice != 4);
    }

    // Send Messages
    public void SendMessages() {

        System.out.print("Please enter the number of messages you want to send: ");

        int number_messages = scanner.nextInt();
        scanner.nextLine();

        if (number_messages <= 0) {
            System.out.println("Please enter a valid number.");
            return;
        }

        cellphone = GetRecipient();

        for (int messages = 1; messages <= number_messages; messages++) {
            System.out.println("\nMessage " + messages);

            Message = GetMessage();

            UniqueMessageId = GenerateMessageID();
            currentMessageNumber = messages - 1;
            int choice = MessageOptions();

            switch (choice) {

                case 1 -> SendMessage();
                case 2 -> DisregardMessage();
                case 3 -> StoreMessage();
            }
            System.out.println(
                    "Total messages sent: " + MessagesSent);
        }
    }

    // Get the recipient's number
    public String GetRecipient() {
        System.out.print("Please enter the recipient cellphone number: ");
        String recipient = scanner.nextLine();

        while (!key1.cellphone_Code(recipient)) {
            key1.cellphone_validator(recipient);
            System.out.print("Please re-enter the cellphone number: ");
            recipient = scanner.nextLine();
        }
        key1.cellphone_validator(recipient);
        return recipient;
    }

    // Get the user's message
    public String GetMessage() {
        System.out.print("Type your message here: ");
        String message = scanner.nextLine();
        
        while (message.trim().isEmpty() || !MessageLength(message)) {
            System.out.println("Please enter a message between 1 and 250 characters.");
            System.out.print("Please re-enter your message: ");
            message = scanner.nextLine();
        }
        return message;
    }

    // The Chat App's Message Option 
    public int MessageOptions() {
        int choice;
        do {
            System.out.println("Please choose from the following options:");
            System.out.println("1. Send Message");
            System.out.println("2. Disregard Message");
            System.out.println("3. Store Message");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > 3) {
                System.out.println("Invalid option!");
            }
        } 
        while (choice < 1 || choice > 3);
        return choice;
    }

    // SThe send message function 
    public void SendMessage() {
        checkSentSpace();
        
        sentMessage[sentCount] = Message;
        sentSender[sentCount] = getSender();
        sentRecipient[sentCount] = cellphone;

        messageID[sentCount] = String.valueOf(UniqueMessageId);
        messageHash[sentCount] = GenerateMessageHash();

        sentCount++;
        MessagesSent++;

        MessageLengthValidator();
    }

    //The Disregard Message Function
    public void DisregardMessage() {

        checkDiscardedSpace();
        disregardedMessage[discardedCount] = Message;
        discardedCount++;
        System.out.println("Message disregarded successfully!");
    }

    // This function stores the user message
    public void StoreMessage() {
        jsonFile();
        System.out.println("Message successfully stored!");
    }

    // This generates the user Message ID 
    public long GenerateMessageID() {
        long id;
        boolean exists;
        do {
            id = random.nextLong(9000000000L) + 1000000000L;
            exists = false;
            for (int i = 0; i < sentCount; i++) {
                if (messageID[i].equals(String.valueOf(id))) {
                    exists = true;
                }
            }

            for (int i = 0; i < storedCount; i++) {
                if (storedIDs[i].equals(String.valueOf(id))) {
                    exists = true;
                }
            }

        }
        while (exists);
        return id;
    }

    //This function checks the Message ID
    public boolean CheckMessageID(String MessageID) {
        if (MessageID == null) {
            return false;
        }
        return MessageID.matches("[0-9]{10}");
    }

    // This function generates the Messsage Hash
    public String GenerateMessageHash() {
        if (Message == null || Message.trim().isEmpty()) {
            return "";
        }
        String id = String.valueOf(UniqueMessageId);
        
        if (!CheckMessageID(id)) {
            return "";
        }

        String firstTwo = id.substring(0, 2);

        String[] words = Message.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        firstWord = firstWord.replaceAll("[^a-zA-Z0-9]", "");
        lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "");

        String hash = firstTwo + ":" + currentMessageNumber + ":"+ (firstWord + lastWord).toUpperCase();
        return hash;
    }

    // This Displays the user's Message Hash
    public void MessageHash() {
        System.out.println(GenerateMessageHash());
    }

    // This checks the Message Length
    public boolean MessageLength(String Message) {
        if (Message == null) {
            return false;
        }
        return Message.length() <= 250;
    }

    //This function validates the Message length
    public void MessageLengthValidator() {

        if (MessageLength(Message)) {

            System.out.println("Message sent!");
            System.out.println("Message ID: " + UniqueMessageId);

            System.out.print("Message Hash: ");
            MessageHash();

            System.out.println("Message Recipient's number: " + cellphone);

            System.out.println("Message: " + Message);
        } 
        else {
            System.out.println("Please enter a message of less than 250 characters");
        }
    }

    // This gets the user's login details 
    public String getSender() {

        if (Login.user == null) {
            return "Unknown";
        }
        return Login.user;
    }

    // This shwos the sent messages
    public void ShowSentMessages() {
        if (sentCount == 0) {
            System.out.println("No messages have been sent.");
            return;
        }
        for (int i = 0; i < sentCount; i++) {

            System.out.println("\nMessage " + (i + 1));
            System.out.println("Recipient: " + sentRecipient[i]);
            System.out.println("Message: " + sentMessage[i]);
        }
    }

    //This function increase the sent message's arrays
    public void checkSentSpace() {
        if (sentCount >= sentMessage.length) {
            int size = sentMessage.length * 2;

            sentMessage = Arrays.copyOf(sentMessage, size);
            sentSender = Arrays.copyOf(sentSender, size);
            sentRecipient = Arrays.copyOf(sentRecipient, size);
            messageID = Arrays.copyOf(messageID, size);
            messageHash = Arrays.copyOf(messageHash, size);
        }
    }

    //This function increases the discarded arrays
    public void checkDiscardedSpace() {
        if (discardedCount >= disregardedMessage.length) {
            disregardedMessage = Arrays.copyOf(disregardedMessage,disregardedMessage.length * 2);
        }
    }

    //This functions increase the stored message array size
    public void checkStoredSpace() {
        if (storedCount >= storedMessage.length) {
            int size = storedMessage.length * 2;

            storedMessage = Arrays.copyOf(storedMessage, size);
            storedSender = Arrays.copyOf(storedSender, size);
            storedRecipient = Arrays.copyOf(storedRecipient, size);
            storedIDs = Arrays.copyOf(storedIDs, size);
            storedHashes = Arrays.copyOf(storedHashes, size);
        }
    }

    //This function stores the message in arrays and json
    public void jsonFile() {
        if (Message == null) {
            System.out.println("No message to store.");
            return;
        }

        if (!CheckMessageID(String.valueOf(UniqueMessageId))) {
            UniqueMessageId = GenerateMessageID();
        }
        checkStoredSpace();

        storedMessage[storedCount] = Message;
        storedSender[storedCount] = getSender();
        storedRecipient[storedCount] = cellphone;

        storedIDs[storedCount] = String.valueOf(UniqueMessageId);
        storedHashes[storedCount] = GenerateMessageHash();

        storedCount++;

        SaveStoredMessages();
    }

    // This function replaces the special characters that the user may type within the message
    public String escapeJson(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    //This function saves the stored message to JSON 
    public void SaveStoredMessages() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Messages.json"))) {

            writer.write("[");
            writer.newLine();

            for (int i = 0; i < storedCount; i++) {

                writer.write("  {");
                writer.write("\"sender\":\"" + escapeJson(storedSender[i]) + "\",");
                writer.write("\"recipient\":\"" + escapeJson(storedRecipient[i]) + "\",");
                writer.write("\"messageID\":\"" + escapeJson(storedIDs[i]) + "\",");
                writer.write("\"messageHash\":\"" + escapeJson(storedHashes[i]) + "\",");
                writer.write("\"message\":\"" + escapeJson(storedMessage[i]) + "\"");
                writer.write("}");
                if (i < storedCount - 1) {
                    writer.write(",");
                }
                writer.newLine();
            }

            writer.write("]");
            System.out.println("Data saved!");
        } 
        catch (IOException e) {
            System.out.println(
                    "Error saving data: " + e.getMessage());
        }
    }

    // This function reads a value from JSON
    public String getJsonValue(String line, String field) {
        
        String search = "\"" + field + "\":\"";
        int start = line.indexOf(search);

        if (start == -1) {
            return "";
        }

        start = start + search.length();
        StringBuilder value = new StringBuilder();
        boolean escaped = false;

        for (int i = start; i < line.length(); i++) {
            char c = line.charAt(i);
            if (escaped) {
                switch (c) {
                    case 'n' -> value.append('\n');
                    case 'r' -> value.append('\r');
                    case 't' -> value.append('\t');
                    default -> value.append(c);
                }
                escaped = false;
            } 
            else if (c == '\\') {
                escaped = true;
            }
            else if (c == '"') {
                break;
            } 
            else {
                value.append(c);
            }
        }

        return value.toString();
    }

    // This function loads the messages from JSON
    public void loadStoredMessages() {

        storedCount = 0;

        File file = new File("Messages.json");
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("{")) {

                    checkStoredSpace();
                    storedSender[storedCount] = getJsonValue(line, "sender");
                    storedRecipient[storedCount] = getJsonValue(line, "recipient");
                    storedIDs[storedCount] = getJsonValue(line, "messageID");
                    storedHashes[storedCount] = getJsonValue(line, "messageHash");
                    storedMessage[storedCount] = getJsonValue(line, "message");
                    storedCount++;
                }
            }

        } catch (IOException e) {
            System.out.println( "Error reading data: " + e.getMessage());
        }
    }

    // This function raeds the stored messages
    public void ReadJsonFile() {

        loadStoredMessages();
        if (storedCount == 0) {
            System.out.println("No stored messages found.");
            return;
        }

        for (int i = 0; i < storedCount; i++) {
            System.out.println("\nMessage ID: " + storedIDs[i]);
            System.out.println("Sender: " + storedSender[i]);
            System.out.println("Recipient: " + storedRecipient[i]);
            System.out.println("Message Hash: " + storedHashes[i]);
            System.out.println("Message: " + storedMessage[i]);
        }
    }

    // The function shows the psrt 3 stored messages menu 
    public void StoredMessages() {

        loadStoredMessages();
        int choice;
        do {
            System.out.println("\n--- STORED MESSAGES ---");
            System.out.println("1. Display senders and recipients");
            System.out.println("2. Display longest message");
            System.out.println("3. Search by Message ID");
            System.out.println("4. Search by recipient");
            System.out.println("5. Delete message using hash");
            System.out.println("6. Display message report");
            System.out.println("0. Back to main menu");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> DisplaySendersRecipients();
                case 2 -> DisplayLongestMessage();
                case 3 -> SearchMessageID();
                case 4 -> SearchRecipient();
                case 5 -> DeleteMessage();
                case 6 -> DisplayReport();
                case 0 -> System.out.println("Returning to main menu");
                default -> System.out.println("Invalid choice");
            }

        } while (choice != 0);
    }

    // This function display the senders and recipients
    public void DisplaySendersRecipients() {

        if (sentCount == 0 && storedCount == 0) {
            System.out.println("No messages available.");
            return;
        }
        for (int i = 0; i < sentCount; i++) {
            System.out.println("Sender: " + sentSender[i]);
            System.out.println("Recipient: " + sentRecipient[i]);
        }
        for (int i = 0; i < storedCount; i++) {
            System.out.println("Sender: " + storedSender[i]);
            System.out.println("Recipient: " + storedRecipient[i]);
        }
    }

    // This function finds the longest message
    public void DisplayLongestMessage() {

        String longest = "";
        for (int i = 0; i < sentCount; i++) {
            if (sentMessage[i].length() > longest.length()) {
                longest = sentMessage[i];
            }
        }

        for (int i = 0; i < storedCount; i++) {
            if (storedMessage[i].length() > longest.length()) {
                longest = storedMessage[i];
            }
        }
        if (sentCount == 0 && storedCount == 0) {
            System.out.println("No messages available.");
        } 
        else {
            System.out.println("Longest message: " + longest);
            System.out.println("Length: " + longest.length());
        }
    }

    // This function searches the message by the Message ID 
    public void SearchMessageID() {

        System.out.print("Enter Message ID: ");
        String id = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < sentCount; i++) {
            if (messageID[i].equals(id)) {
                System.out.println("Recipient: " + sentRecipient[i]);
                System.out.println("Message: " + sentMessage[i]);
                found = true;
            }
        }

        for (int i = 0; i < storedCount; i++) {
            if (storedIDs[i].equals(id)) {
                System.out.println("Recipient: " + storedRecipient[i]);
                System.out.println("Message: " + storedMessage[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Message not found.");
        }
    }

    //This function searches the message by the recipient 
    public void SearchRecipient() {

        System.out.print("Enter recipient cellphone number: ");
        String recipient = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < sentCount; i++) {
            if (sentRecipient[i].equals(recipient)) {
                System.out.println("Message: " + sentMessage[i]);
                found = true;
            }
        }
        for (int i = 0; i < storedCount; i++) {
            if (storedRecipient[i].equals(recipient)) {
                System.out.println("Message: " + storedMessage[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found.");
        }
    }

    // This function deletes the message by the Message Hash
    public void DeleteMessage() {
        System.out.print("Enter Message Hash: ");
        String hash = scanner.nextLine();

        for (int i = 0; i < sentCount; i++) {
            if (messageHash[i].equals(hash)) {
                for (int j = i; j < sentCount - 1; j++) {
                    sentMessage[j] = sentMessage[j + 1];
                    sentSender[j] = sentSender[j + 1];
                    sentRecipient[j] = sentRecipient[j + 1];
                    messageID[j] = messageID[j + 1];
                    messageHash[j] = messageHash[j + 1];
                }
                sentCount--;

                sentMessage[sentCount] = null;
                sentSender[sentCount] = null;
                sentRecipient[sentCount] = null;
                messageID[sentCount] = null;
                messageHash[sentCount] = null;

                System.out.println("Message deleted successfully!");
                return;
            }
        }

        for (int i = 0; i < storedCount; i++) {

            if (storedHashes[i].equals(hash)) {
                for (int j = i; j < storedCount - 1; j++) {
                    storedMessage[j] = storedMessage[j + 1];
                    storedSender[j] = storedSender[j + 1];
                    storedRecipient[j] = storedRecipient[j + 1];
                    storedIDs[j] = storedIDs[j + 1];
                    storedHashes[j] = storedHashes[j + 1];
                }

                storedCount--;
                storedMessage[storedCount] = null;
                storedSender[storedCount] = null;
                storedRecipient[storedCount] = null;
                storedIDs[storedCount] = null;
                storedHashes[storedCount] = null;
                SaveStoredMessages();

                System.out.println("Message deleted successfully!");
                return;
            }
        }

        System.out.println("Message hash not found.");
    }

    // This function display the full message reports 
    public void DisplayReport() {
        System.out.println("\n===== MESSAGE REPORT =====");
        if (sentCount == 0 && storedCount == 0) {
            System.out.println("No messages available.");
            return;
        }

        for (int i = 0; i < sentCount; i++) {
            System.out.println("\nSENT MESSAGE " + (i + 1));
            System.out.println("Sender: " + sentSender[i]);
            System.out.println("Recipient: " + sentRecipient[i]);
            System.out.println("Message ID: " + messageID[i]);
            System.out.println("Message Hash: " + messageHash[i]);
            System.out.println("Message: " + sentMessage[i]);
        }

        for (int i = 0; i < storedCount; i++) {
            System.out.println("\nSTORED MESSAGE " + (i + 1));
            System.out.println("Sender: " + storedSender[i]);
            System.out.println("Recipient: " + storedRecipient[i]);
            System.out.println("Message ID: " + storedIDs[i]);
            System.out.println("Message Hash: " + storedHashes[i]);
            System.out.println("Message: " + storedMessage[i]);
        }
    }
}