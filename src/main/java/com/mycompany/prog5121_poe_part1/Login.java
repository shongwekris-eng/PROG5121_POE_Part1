package com.mycompany.prog5121_poe_part1;

import java.util.regex.Pattern;

public class Login {

    private String userName;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    private static final String USERNAME_SUCCESS = "Username successfully captured.";
    private static final String USERNAME_FAIL =
            "Username is not correctly formatted; please ensure that your username "
            + "contains an underscore and is no more than five characters in length.";

    private static final String PASSWORD_SUCCESS = "Password successfully captured.";
    private static final String PASSWORD_FAIL =
            "Password is not correctly formatted; please ensure that the password "
            + "contains at least eight characters, a capital letter, a number, and a "
            + "special character.";

    private static final String CELL_SUCCESS = "Cell phone number successfully added.";
    private static final String CELL_FAIL =
            "Cell phone number incorrectly formatted or does not contain international code.";

    private static final String LOGIN_FAIL = "Username or password incorrect, please try again.";

    public Login() {
    }

    public boolean checkUserName(String userName) {
        if (userName == null) {
            return false;
        }
        return userName.contains("_") && userName.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null) {
            return false;
        }
        boolean longEnough = password.length() >= 8;
        boolean hasCapital = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasDigit = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasSpecial = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
        return longEnough && hasCapital && hasDigit && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber == null) {
            return false;
        }
        return Pattern.matches("^\\+27[0-9]{7,10}$", cellNumber);
    }

    public String registerUser(String userName, String password, String cellNumber,
            String firstName, String lastName) {

        if (!checkUserName(userName)) {
            return USERNAME_FAIL;
        }
        if (!checkPasswordComplexity(password)) {
            return PASSWORD_FAIL;
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return CELL_FAIL;
        }

        this.userName = userName;
        this.password = password;
        this.cellPhoneNumber = cellNumber;
        this.firstName = firstName;
        this.lastName = lastName;

        return USERNAME_SUCCESS + " " + PASSWORD_SUCCESS + " " + CELL_SUCCESS;
    }

    public boolean loginUser(String userName, String password) {
        if (this.userName == null) {
            return false;
        }
        return this.userName.equals(userName) && this.password.equals(password);
    }

    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return LOGIN_FAIL;
    }
}
