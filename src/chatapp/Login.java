/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp;

import java.util.Scanner;

/**
 *
 * @author Acer
 */
public class Login {
        String Name;
        String Surname;
        String Username;
        String Password;
    
        public static void main(String[] args) {

        }
    

    public  boolean numbers(String username) {
        for (char chr : username.toCharArray()) {
            if (Character.isDigit(chr)) {
                return true;
            }

        }
        return false;
    }
    public  boolean special_character(String password){
        for (char chr: password.toCharArray()){
            if (!Character.isLetterOrDigit(chr)){
                return true;
            }
        }
        return false;
    }

    public  boolean underscore(String username) {
        if (username.contains("_")){
            return true;
        }
        return false;
    }

    public  boolean length_(String username) {
        if (username.length() < 5){
            return true;
        }
        return false;
    }
    public static boolean length_password(String password) {
        if (password.length() > 7){
           return true;
        }
        return false;
    }

    public  boolean uppercase(String username) {
        for (char chr : username.toCharArray()) {
            if (Character.isUpperCase(chr)) {
                return true;
            }
        }
        return false;
    }
    public  boolean digit(String password){
        for(char chr: password.toCharArray()){
            if (Character.isDigit(chr)){
                return true;
            }
        }
        return false;
    }

    public  boolean lowercase(String username) {
        for (char chr : username.toCharArray()) {
            if (Character.isLowerCase(chr)) {
                return true;
            }
        }
        return false;
    }

    public  void username_valid(String username) {
        if (underscore(username) && length_(username)) {
            System.out.println("Username captured successfully!");
        } else {
            System.out.println("Username is not correctly formatted! Please ensure that the username contains an underscore and has less than five characters in length");
           
        }
            

    }
    public  void password_validator(String password){
        if (uppercase(password) && special_character(password) && length_password(password) && digit(password) ){
            System.out.println("Password captured successfully ");
        }
        else{
            System.out.println("Password is not correctly formatted! Please ensure that the password has atleast eight characters, a capital letter, a number and a special character");
                }
            
    }
    public  boolean cellphone_Code(String cellphone){
        String regex = "\\+27[0-9]{9}";
        return cellphone.matches(regex);
        } 
    public  void cellphone_validator(String cellphone){
        if (cellphone_Code(cellphone)){
            System.out.println("Cell number captured successfully");
        }
        else{
            System.out.println("Cell number not formatted correctly or does not have an international code, please correct the number and try again ");
            
        }
          
     
    }
    public void loginUser(){
        Scanner key = new Scanner(System.in);
        System.out.println("Welcome to the login page of the ChatApp");
        System.out.print("Please enter your Username here:    ");
        String user = key.nextLine();
        System.out.print("Please enter your password here:   ");
        String user_password = key.nextLine();
        returnUserLoginStatus(user,user_password);
        
        
        
        }
    
    public void registerUser(){
        Scanner scanner = new Scanner(System.in);
                
                System.out.println("Welcome Registration page for the ChatApp");
                System.out.print("Please enter your name    ");
                Name = scanner.nextLine();
                System.out.print("Please enter your surname     ");
                Surname = scanner.nextLine();
                System.out.print("Create your username here :     ");
                Username = scanner.nextLine();
                while(!underscore(Username) || !length_(Username)){
                    username_valid(Username);
                    System.out.print("Re-Create your username here :     ");
                Username = scanner.nextLine();
                }
                username_valid(Username);
                
                
                        
                System.out.println("Welcome " + Name +" "+ Surname +"  it is great to see you again!" );
                
                
                System.out.print("Please create your password here:   ");
                Password =  scanner.nextLine();
                while (!uppercase(Password) || !special_character(Password) || !length_password(Password) || !digit(Password)){
                password_validator(Password);
                System.out.print("Please re-create your password here:   ");
                Password =  scanner.nextLine();
                }
                password_validator(Password);
                
                System.out.print("Please enter your cellphone number here:    ");
                String cellphone = scanner.nextLine();
                while (!cellphone_Code(cellphone)){
                cellphone_validator(cellphone);
                System.out.print("Please re-enter your cellphone number here:    ");
                cellphone = scanner.nextLine();
                }
                cellphone_validator(cellphone);
                System.out.println(Name +"  "+ Surname +"   have been successfully registered!");
                
                
                
        }
    public boolean returnLoginStatus(String user, String user_password){
        if (Username.equals(user )&& Password.equals(user_password)){
            return true;
        }
        return false;
    }
    public void returnUserLoginStatus(String username, String password){
        if (returnLoginStatus(username,password)){
            System.out.println("Login Successful");
        }
        else {
            System.out.println("Login Failed");
        }
        
    }
            
            
            

    }