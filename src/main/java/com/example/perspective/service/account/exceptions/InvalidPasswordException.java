package com.example.perspective.service.account.exceptions;

/**
 * Exception thrown if an incorrect password is entered at login.
 */
public class InvalidPasswordException extends Exception {

    /**
     * This constructor is called when the exception is thrown, displaying a detailed diagnosis of the error.
     *
     * @param message containing the details of the error
     */
    public InvalidPasswordException(String message) {
        super(message);
    }
}