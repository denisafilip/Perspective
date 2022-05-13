package com.example.perspective.service.validators;

import com.example.perspective.service.exceptions.InvalidDataException;

/**
 * Validator of a number.
 */
public class NumberValidator implements Validator {

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(Object obj) throws InvalidDataException {
        Integer number = (Integer) obj;
        if (number == null || number < 0) {
            throw new InvalidDataException("The number " + number + " is invalid!");
        }
    }
}
