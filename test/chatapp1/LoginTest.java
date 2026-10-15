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
public class LoginTest {
    
    public LoginTest() {
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
     * Test of main method, of class Login.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Login.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numbers method, of class Login.
     */
    @Test
    public void testNumbers() {
        System.out.println("numbers");
        String username = "_Meo";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.numbers(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of special_character method, of class Login.
     */
    @Test
    public void testSpecial_character() {
        System.out.println("special_character");
        String password = "Gontse!123";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.special_character(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of underscore method, of class Login.
     */
    @Test
    public void testUnderscore() {
        System.out.println("underscore");
        String username = "_Meo";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.underscore(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of length_ method, of class Login.
     */
    @Test
    public void testLength_() {
        System.out.println("length_");
        String username = "_Meo";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.length_(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of length_password method, of class Login.
     */
    @Test
    public void testLength_password() {
        System.out.println("length_password");
        String password = "Gontse!123";
        boolean expResult = false;
        boolean result = Login.length_password(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of uppercase method, of class Login.
     */
    @Test
    public void testUppercase() {
        System.out.println("uppercase");
        String username = "_Meo";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.uppercase(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of digit method, of class Login.
     */
    @Test
    public void testDigit() {
        System.out.println("digit");
        String password = "Gontse!123";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.digit(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lowercase method, of class Login.
     */
    @Test
    public void testLowercase() {
        System.out.println("lowercase");
        String username = "_Meo";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.lowercase(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of username_valid method, of class Login.
     */
    @Test
    public void testUsername_valid() {
        System.out.println("username_valid");
        String username = "_meo";
        Login instance = new Login();
        instance.username_valid(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of password_validator method, of class Login.
     */
    @Test
    public void testPassword_validator() {
        System.out.println("password_validator");
        String password = "Gontse!123";
        Login instance = new Login();
        instance.password_validator(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cellphone_Code method, of class Login.
     */
    @Test
    public void testCellphone_Code() {
        System.out.println("cellphone_Code");
        String cellphone = "+27655667272";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.cellphone_Code(cellphone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cellphone_validator method, of class Login.
     */
    @Test
    public void testCellphone_validator() {
        System.out.println("cellphone_validator");
        String cellphone = "+2765667272";
        Login instance = new Login();
        instance.cellphone_validator(cellphone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        Login instance = new Login();
        instance.loginUser();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        Login instance = new Login();
        instance.registerUser();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        String user = "_Meo";
        String user_password = "Gontse!123";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.returnLoginStatus(user, user_password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnUserLoginStatus method, of class Login.
     */
    @Test
    public void testReturnUserLoginStatus() {
        System.out.println("returnUserLoginStatus");
        String username = "_Meo";
        String password = "Gontse!123";
        Login instance = new Login();
        instance.returnUserLoginStatus(username, password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
