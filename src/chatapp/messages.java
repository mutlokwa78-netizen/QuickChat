/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Acer
 */
public class messages {
     static int MessageID;

    public static void main(String[]args){
        Login key1 = new Login();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        
        System.out.println("Welcome to QuickChat!");
        System.out.println("Please choose from the following actions that you want to complete");
        System.out.println("1. Send Messages");
        System.out.println("2. Show recently sent messages");
        System.out.println("3. Quit");
        int choice = scanner.nextInt();
            
        switch (choice){
            case 1 -> {
                System.out.print("Please enter the messages you want to send");
                int number_messages = scanner.nextInt();
                int messages;
                for (messages =0;messages <= number_messages;messages++){

                    System.out.print("Please enter your recipient's number here:    ");
                    String recipient = scanner.nextLine();
                    key1.cellphone_validator(recipient);
                    System.out.println("Type your message here:     ");
                    String message = scanner.nextLine();
                    System.out.println("Please choose from the following options");
                    System.out.println("1. Send Message");
                    System.out.println("2. Press '0' to delete the message");
                    System.out.println("3. Store your message");
                    System.out.print("Please enter your choice here:  ");
                    int choice2 = scanner.nextInt();
                    
                    switch (choice2){
                        case 1 -> {
                            if (message.length() <= 250){
                                System.out.println("Message Sent");
                                MessageID = random.nextInt(10)+1;
                            }
                            else{
                                System.out.println("Please enter a message less than 250 Characters ");  
                            }   
                        }
                                
                                
                                
                                
                                }
                        
                    
                    
                    }
                    
                }
                
        
         }
    }
    public boolean CheckMessageID(String MessageID){
        if (MessageID.length() == 10){
            return true;
        }
        return false;   
    }
    public static String melodius(String me){
        return "me";
    }
}
       


   
