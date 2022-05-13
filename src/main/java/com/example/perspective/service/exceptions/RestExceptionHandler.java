package com.example.perspective.service.exceptions;

import com.example.perspective.service.account.exceptions.DuplicateEmailException;
import com.example.perspective.service.account.exceptions.NoSuchAccountException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles exceptions thrown by REST APIs.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }

    @ExceptionHandler(InvalidDataException.class)
    protected ResponseEntity<ApiError> handleInvalidInputException(InvalidDataException exception, WebRequest webRequest) {
        String error = "Invalid input data exception";

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), error);

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(NoSuchAccountException.class)
    protected ResponseEntity<ApiError> handleNoSuchAccountException(NoSuchAccountException exception, WebRequest webRequest) {
        String error = "No such account exists exception";

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), error);

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(DuplicateEmailException.class)
    protected ResponseEntity<ApiError> handleDuplicateEmailException(DuplicateEmailException exception, WebRequest webRequest) {
        String error = "This email already exists";

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), error);

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}