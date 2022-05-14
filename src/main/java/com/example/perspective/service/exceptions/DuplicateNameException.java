package com.example.perspective.service.exceptions;

/**
 * Exception thrown if the name of an object already exists in the database.
 */
public class DuplicateNameException extends Exception {

    /**
     * This constructor is called when the exception is thrown, displaying a detailed diagnosis of the error.
     *
     * @param message containing the details of the error
     */
    public DuplicateNameException(String message) {
        super(message);
    }
}