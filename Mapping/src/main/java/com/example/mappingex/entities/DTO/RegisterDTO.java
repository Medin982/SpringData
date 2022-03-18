package com.example.mappingex.entities.DTO;

import com.example.mappingex.Exceptions.ValidationExceptions;

import javax.xml.bind.ValidationException;

public class RegisterDTO {

    private String email;

    private String password;

    private String confirmPassword;

    private String fullName;

    public RegisterDTO(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        this.validateDate();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void validateDate() {
//  Email – must contain @ sign and a period.
        int indexOfAt = email.indexOf("@");
        int indexOfDot = email.lastIndexOf(".");
        if (indexOfAt < 0 || indexOfDot < 0 || indexOfAt > indexOfDot) {
            throw new ValidationExceptions("Email must be contain @ and .");
        }

//        Password – length must be at least 6 symbols and must contain at least 1 uppercase,
//        1 lowercase letter and 1 digit.
        if (!password.equals(confirmPassword)) {
            throw new ValidationExceptions("Password not match");
        } else if (password.length() < 6) {
            throw new ValidationExceptions("Password length must be at least 6 symbols");
        } else {
            boolean containsUpper = false;
            boolean containsLower = false;
            boolean containsDigit = false;
            char[] chars = password.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (Character.isAlphabetic(chars[i])) {
                    if (Character.isUpperCase(chars[i])) {
                        containsUpper = true;
                    } else {
                        containsLower = true;
                    }
                } else if (Character.isDigit(chars[i])) {
                    containsDigit = true;
                }
            }
            if (!containsDigit || !containsLower || !containsUpper) {
                throw new ValidationExceptions("Password must contain at least 1 uppercase, 1 lowercase letter and 1 digit.");
            }
        }
    }
}
