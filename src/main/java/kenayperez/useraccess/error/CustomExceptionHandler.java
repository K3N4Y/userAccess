package kenayperez.useraccess.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import kenayperez.useraccess.dto.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleEmailExists(EmailAlreadyExistException ex) {
        ErrorResponse response = new ErrorResponse("EMAIL_ALREADY_EXISTS", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
