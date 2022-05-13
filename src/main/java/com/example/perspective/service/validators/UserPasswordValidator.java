package com.example.perspective.service.validators;

import com.example.perspective.service.exceptions.InvalidDataException;

/**
 * Validator of a password.
 */
public class UserPasswordValidator implements Validator {
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-_@$!%*?&])[A-Za-z\\d@_$!%*?&]{6,}$";

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(Object obj) throws InvalidDataException {
        String password = (String) obj;
        if (password == null || password.isEmpty() || !password.matches(PASSWORD_REGEX)) {
            throw new InvalidDataException("The password must have at least 6 characters, an uppercase letter, a lowercase letter, " +
                    "a special character and a digit.");
        }
    }
}
