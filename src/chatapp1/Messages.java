/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp1;
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 *
 * @author Acer
 */
public class Messages {
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    Login key1 = new Login();
    static String sentMessage[];
    static String disregardedMessage[];
    static String storedMessage[];
    static String messageHash[];
    static String messageID[];    
    static int UniqueMessageId;
    static String cellphone;
    static String Message;
    static int MessagesSent;

    public void GreetingPage(){
           
        System.out.println("");
        
        System.out.println("Welcome to QuickChat!");
        System.out.println("Please choose from the following actions that you want to complete");
        System.out.println("1. Send Messages");
        System.out.println("2. Show recently sent messages");
        System.out.println("3. Stored Messages  ");
        System.out.println("4. Quit");
        int choice = scanner.nextInt();
        do {
        switch (choice){
            case 1 -> {
                SendMessages();
            }
            case 2 ->{
                ReadJsonFile();
                
            }
            case 3 -> {
                StoredMessages();
            }
            case 4 -> {
                
                System.out.println("Thank you for using QuickChat");
            }
            default -> {
                System.out.println("Invalid option! Please try again");
            }
        }
        }while (choice !=3);
            

    }
    public void SendMessages(){
    int messages=1;
    System.out.print("Please enter the number of messages you want to send");
    int number_messages = scanner.nextInt();
    scanner.nextLine();
    System.out.print("Please the recipient cellphone number here:    ");
    cellphone = scanner.nextLine();
    while (!key1.cellphone_Code(cellphone)){
    key1.cellphone_validator(cellphone);
    System.out.print("Please re-enter your cellphone number here:    ");
    cellphone = scanner.nextLine();
    }
    key1.cellphone_validator(cellphone);
    while (messages<= number_messages){
        scanner.nextLine();
        System.out.println("Type your message here:     ");
        Message = scanner.nextLine();
        System.out.println("Please choose from the following options");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. store message for later");
        
        
        MessagesSent = messages;
        UniqueMessageId = random.nextInt(1000000000)+999999999;
       
        
        System.out.print("Please enter your choice here:  ");
        int choice2 = scanner.nextInt();

        switch (choice2)   {
            case 1 -> {
                MessageLengthValidator();
                
            
            }
            case 2-> {
                
                System.out.println("Press '0' to delete the message");
                System.out.print("Please enter here:    ");
                int choice3 = scanner.nextInt();
                switch (choice3){
                    case 0 -> {
                        System.out.println("Message delete successfully!");
                    }
                }
                
            }
            case 3->{
                jsonFile();
            }
            default ->{
                System.out.println("Please enter a valid option");
            }
        

        } 
        System.out.println("The number of messages sent:    "+MessagesSent);
        messages++;
    }
    }





    public boolean CheckMessageID(String MessageID){
        if (MessageID.length() == 10){
            return true;
        }
        return false;   
    }
    public void MessageHash(){
        String messageID = String.valueOf(UniqueMessageId);
        
        char firstV = messageID.charAt(0);
        char secondV = messageID.charAt(1);
        
        String MessageIdValue = String.valueOf(firstV +""+ secondV);
        
        String[] arrar = Message.split(" ");
        String firstWord = arrar[0];
        String lastWord = arrar[arrar.length -1];
        System.out.println(MessageIdValue+":"+MessagesSent+":"+firstWord+lastWord);

       
    }
    public boolean MessageLength( String Message){
        if (Message.length() <=250){
            return true;
        }
        return false;
        
    }
    public void MessageLengthValidator(){
        if (MessageLength(Message)){
          System.out.println("Message sent!");
          System.out.println("Message ID: "+UniqueMessageId);
          System.out.print("Message Hash:   ");
          MessageHash();
          System.out.println("Message Recipient's number :    "+cellphone);
          System.out.println("Message:    "+Message);    
        }
        else{
         System.out.println("Please enter a message of less tham 250 characters");
        }
    }

    
    
    
    
    
    
    
    
    public  void jsonFile(){
         try {
            FileWriter filewriter = new FileWriter("Messages.json");
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
            
            bufferedwriter.write("{\"Message\":\"" + Message );

            bufferedwriter.close();

            System.out.println("Data saved!");

        } catch (IOException e) {
            System.out.println("Error saving data");
        }
    
    }
    public void ReadJsonFile(){
        try {
            FileReader filereader = new FileReader("Messages.json");
            BufferedReader bufferedreader = new BufferedReader(filereader);

            while (bufferedreader.readLine() != null ){
                String data = bufferedreader.readLine();
                System.out.println(data);
            }

            bufferedreader.close();

        } catch (Exception e) {
            System.out.println("File not found!");
        }
    }
    
    public void StoredMessages(){
           
    }
    
    
}


       


   
