package com.mycompany.prog5121_poe_part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
    }

    // ---------- Username ----------

    @Test
    public void testUserNameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUserNameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    // ---------- Password ----------

    @Test
    public void testPasswordMeetsComplexityRules() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRules() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // ---------- Cell phone number ----------

    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneNumberIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // ---------- Registration messages ----------

    @Test
    public void testRegisterUser_UsernameFails() {
        String result = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals(
            "Username is not correctly formatted; please ensure that your username "
            + "contains an underscore and is no more than five characters in length.",
            result);
    }

    @Test
    public void testRegisterUser_PasswordFails() {
        String result = login.registerUser("kyl_1", "password", "+27838968976", "Kyle", "Smith");
        assertEquals(
            "Password is not correctly formatted; please ensure that the password "
            + "contains at least eight characters, a capital letter, a number, and a "
            + "special character.",
            result);
    }

    @Test
    public void testRegisterUser_CellNumberFails() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553", "Kyle", "Smith");
        assertEquals(
            "Cell phone number incorrectly formatted or does not contain international code.",
            result);
    }

    // ---------- Login ----------

    @Test
    public void testLoginSuccessful() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertFalse(login.loginUser("kyl_1", "WrongPassword1!"));
    }

    @Test
    public void testReturnLoginStatus_Success() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        boolean success = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle, Smith it is great to see you again.", login.returnLoginStatus(success));
    }

    @Test
    public void testReturnLoginStatus_Failure() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        boolean success = login.loginUser("kyl_1", "WrongPassword1!");
        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus(success));
    }
}