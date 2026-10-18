
package chatapp1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private InputStream originalInput;
    private PrintStream originalOutput;

    @BeforeEach
    public void setUp() {
        originalInput = System.in;
        originalOutput = System.out;
        Login.user = null;
        Login.user_password = null;
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
        Login.user = null;
        Login.user_password = null;
    }

    // Simulate keyboard input
    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(
                input.getBytes(StandardCharsets.UTF_8)));
    }

    // Capture printed output
    private String getOutput(Runnable action) {
        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        try (PrintStream capture = new PrintStream(output)) {
            System.setOut(capture);
            action.run();
        } finally {
            System.setOut(originalOutput);
        }

        return output.toString(StandardCharsets.UTF_8);
    }

    // Create a registered account for testing
    private Login registeredUser() {
        Login login = new Login();
        login.Name = "Kyle";
        login.Surname = "Smith";
        login.Username = "kyl_1";
        login.Password = "Ch&&sec@ke99!";
        return login;
    }

    // TEST 1: Username contains numbers
    @Test
    public void testNumbers() {
        Login login = new Login();

        assertTrue(login.numbers("kyl_1"));
        assertFalse(login.numbers("kyl_a"));
    }

    // TEST 2: Password special character
    @Test
    public void testSpecial_character() {
        Login login = new Login();

        assertTrue(login.special_character("Ch&&sec@ke99!"));
        assertFalse(login.special_character("Password123"));
    }

    // TEST 3: Username underscore
    @Test
    public void testUnderscore() {
        Login login = new Login();

        assertTrue(login.underscore("kyl_1"));
        assertFalse(login.underscore("kyle"));
    }

    // TEST 4: Username length
    @Test
    public void testLength_() {
        Login login = new Login();

        assertTrue(login.length_("kyl_1"));
        assertFalse(login.length_("kyle!!!!!!!"));
    }

    // TEST 5: Password length
    @Test
    public void testLength_password() {

        assertTrue(Login.length_password("Ch&&sec@ke99!"));
        assertFalse(Login.length_password("pass123"));
    }

    // TEST 6: Uppercase character
    @Test
    public void testUppercase() {
        Login login = new Login();

        assertTrue(login.uppercase("Ch&&sec@ke99!"));
        assertFalse(login.uppercase("password123"));
    }

    // TEST 7: Password contains digit
    @Test
    public void testDigit() {
        Login login = new Login();

        assertTrue(login.digit("Ch&&sec@ke99!"));
        assertFalse(login.digit("Password!"));
    }

    // TEST 8: Lowercase character
    @Test
    public void testLowercase() {
        Login login = new Login();

        assertTrue(login.lowercase("kyl_1"));
        assertFalse(login.lowercase("KYL_1"));
    }

    // TEST 9: Username correctly formatted
    @Test
    public void testUsername_valid() {
        Login login = new Login();

        String output = getOutput(() ->
                login.username_valid("kyl_1"));

        assertEquals(
                "Username captured successfully!",
                output.trim());
    }

    // TEST 10: Username incorrectly formatted
    @Test
    public void testUsername_invalid() {
        Login login = new Login();

        String output = getOutput(() ->
                login.username_valid("kyle!!!!!!!"));

        assertTrue(output.contains(
                "Username is not correctly formatted"));
    }

    // TEST 11: Password meets requirements
    @Test
    public void testPassword_validator() {
        Login login = new Login();

        String output = getOutput(() ->
                login.password_validator("Ch&&sec@ke99!"));

        assertEquals(
                "Password successfully captured.",
                output.trim());
    }

    // TEST 12: Invalid password
    @Test
    public void testPassword_invalid() {
        Login login = new Login();

        String output = getOutput(() ->
                login.password_validator("password"));

        assertEquals(
                "Password is not correctly formatted; please "
                + "ensure that the password contains at least "
                + "eight characters, a capital letter, a number, "
                + "and a special character.",
                output.trim());
    }

    // TEST 13: Cellphone format
    @Test
    public void testCellphone_Code() {
        Login login = new Login();

        assertTrue(login.cellphone_Code("+27838968976"));
        assertFalse(login.cellphone_Code("08966553"));
    }

    // TEST 14: Valid cellphone response
    @Test
    public void testCellphone_validator() {
        Login login = new Login();

        String output = getOutput(() ->
                login.cellphone_validator("+27838968976"));

        assertEquals(
                "Cell number successfully captured.",
                output.trim());
    }

    // TEST 15: Invalid cellphone response
    @Test
    public void testCellphone_invalid() {
        Login login = new Login();

        String output = getOutput(() ->
                login.cellphone_validator("08966553"));

        assertTrue(output.contains(
                "international code"));
    }

    // TEST 16: Successful login
    @Test
    public void testReturnLoginStatusSuccess() {
        Login login = registeredUser();

        assertTrue(login.returnLoginStatus(
                "kyl_1", "Ch&&sec@ke99!"));
    }

    // TEST 17: Failed login
    @Test
    public void testReturnLoginStatusFailure() {
        Login login = registeredUser();

        assertFalse(login.returnLoginStatus(
                "kyl_1", "wrongpassword"));

        assertFalse(login.returnLoginStatus(
                "wrongname", "Ch&&sec@ke99!"));
    }

    // TEST 18: Registration
    @Test
    public void testRegisterUser() {

        setInput(
                "Kyle\n"
                + "Smith\n"
                + "kyl_1\n"
                + "Ch&&sec@ke99!\n"
                + "+27838968976\n");

        Login login = new Login();

        String output = getOutput(() ->
                login.registerUser());

        assertEquals("Kyle", login.Name);
        assertEquals("Smith", login.Surname);
        assertEquals("kyl_1", login.Username);
        assertEquals("Ch&&sec@ke99!", login.Password);

        assertTrue(output.contains(
                "Welcome Kyle, Smith it is great to see you."));
    }

    // TEST 19: Invalid details during registration
    @Test
    public void testRegisterUserRetry() {

        setInput(
                "Kyle\n"
                + "Smith\n"
                + "kyle!!!!!!!\n"
                + "kyl_1\n"
                + "password\n"
                + "Ch&&sec@ke99!\n"
                + "08966553\n"
                + "+27838968976\n");

        Login login = new Login();

        String output = getOutput(() ->
                login.registerUser());

        assertEquals("kyl_1", login.Username);
        assertEquals("Ch&&sec@ke99!", login.Password);

        assertTrue(output.contains(
                "Username is not correctly formatted"));

        assertTrue(output.contains(
                "Password is not correctly formatted"));

        assertTrue(output.contains(
                "international code"));
    }

    // TEST 20: Login using keyboard input
    @Test
    public void testLoginUser() {

        setInput("kyl_1\nCh&&sec@ke99!\n");

        Login login = registeredUser();

        String output = getOutput(() ->
                login.loginUser());

        assertEquals("kyl_1", Login.user);

        assertTrue(output.contains("Login Successful"));
    }

    // TEST 21: Return user login status
    @Test
    public void testReturnUserLoginStatus() {

        Login login = registeredUser();

        String output = getOutput(() ->
                login.returnUserLoginStatus(
                        "kyl_1", "Ch&&sec@ke99!"));

        assertTrue(output.contains("Login Successful"));
    }

    // TEST 22: Login retry after incorrect credentials
    @Test
    public void testLoginRetry() {

        setInput("kyl_1\nCh&&sec@ke99!\n");

        Login login = registeredUser();

        String output = getOutput(() ->
                login.returnUserLoginStatus(
                        "wrongname", "wrongpassword"));

        assertTrue(output.contains("Login Failed"));
        assertTrue(output.contains("Login Successful"));
    }
}
