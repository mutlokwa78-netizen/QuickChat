/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chatapp;
import java.util.Scanner;
/**
 *
 * @author Acer
 */
public class ChatApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login key = new Login();

        while (true){       
        System.out.println("");
        System.out.println("Welcome to the Chatapp!");
        System.out.println("Please choose from the following actions that you want to complete");
        System.out.println("1. Register");
        System.out.println("2. Login ");
        System.out.print("Please enter your choice:     ");
        int choice = scanner.nextInt();
        
        switch (choice){
            case 1 -> {
            key.registerUser();

            }
            case 2 -> {
            key.loginUser();
            }

    }}
    
    }
}
