/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package chatapp1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Acer
 */
public class MessagesTest {
    
    public MessagesTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class Messages.
     */
    @Test
   public void testMain() {
        System.out.println("main");
        String[] args = null;
        Messages.main(args);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of GreetingPage method, of class Messages.
     */
    @Test
    public void testGreetingPage() {
        System.out.println("GreetingPage");
        Messages instance = new Messages();
        instance.GreetingPage();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of SendMessages method, of class Messages.
     */
    @Test
    public void testSendMessages() {
        System.out.println("SendMessages");
        Messages instance = new Messages();
        instance.SendMessages();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of CheckMessageID method, of class Messages.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("CheckMessageID");
        String MessageID = "0123456789";
        Messages instance = new Messages();
        boolean expResult = false;
        boolean result = instance.CheckMessageID(MessageID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of MessageHash method, of class Messages.
     */
    @Test
    public void testMessageHash() {
        System.out.println("MessageHash");
        Messages instance = new Messages();
        instance.MessageHash();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of MessageLength method, of class Messages.
     */
    @Test
    public void testMessageLength() {
        System.out.println("MessageLength");
        String Message = "";
        Messages instance = new Messages();
        boolean expResult = false;
        boolean result = instance.MessageLength(Message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of MessageLengthValidator method, of class Messages.
     */
    @Test
    public void testMessageLengthValidator() {
        System.out.println("MessageLengthValidator");
        Messages instance = new Messages();
        instance.MessageLengthValidator();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of jsonFile method, of class Messages.
     */
    @Test
    public void testJsonFile() {
        System.out.println("jsonFile");
        Messages instance = new Messages();
        instance.jsonFile();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of ReadJsonFile method, of class Messages.
     */
    @Test
    public void testReadJsonFile() {
        System.out.println("ReadJsonFile");
        Messages instance = new Messages();
        instance.ReadJsonFile();
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
