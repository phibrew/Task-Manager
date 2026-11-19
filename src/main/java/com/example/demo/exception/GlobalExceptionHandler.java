package com.example.demo.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException e){
        return new ResponseEntity<>(
                convertToErrorHandler(HttpStatus.BAD_REQUEST,
                        "Invalid operation: The requested record references a User, Workspace, or Board that does not exist.")
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleException(BadCredentialsException e){
        return new ResponseEntity<>(
                convertToErrorHandler(HttpStatus.UNAUTHORIZED,
                        e.getMessage()!=null? e.getMessage():"Invalid username or password"),
                HttpStatus.UNAUTHORIZED);
    }
    // this if for the valid input
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e){
        StringBuilder errorMessage = new StringBuilder();

        for(FieldError fieldError : e.getBindingResult().getFieldErrors()){
            errorMessage.append(fieldError.getDefaultMessage()).append(" ");
        }
        return new ResponseEntity<>(
                convertToErrorHandler(HttpStatus.BAD_REQUEST,
                        errorMessage.toString().trim())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        return new ResponseEntity<>(
                convertToErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR,
                        e.getMessage()!=null ? e.getMessage() : "An Unexpected Server Error Occurred."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse convertToErrorHandler(HttpStatus status, String message){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(status.value());
        errorResponse.setError("Bad Request");
        return errorResponse;
    }
}
