package by.schesnovich.CarRentApp.error.handler;

import by.schesnovich.CarRentApp.error.exception.ApiException;
import by.schesnovich.CarRentApp.error.exception.CarAlreadyRentedException;
import by.schesnovich.CarRentApp.error.exception.ResourceNotFoundException;
import by.schesnovich.CarRentApp.error.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ApiException apiException = new ApiException(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CarAlreadyRentedException.class})
    public ResponseEntity<Object> handleCarAlreadyRentedException(CarAlreadyRentedException exception) {
        ApiException apiException = new ApiException(exception.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(apiException, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<Object> UserAlreadyExistsException(UserAlreadyExistsException exception) {
        ApiException apiException = new ApiException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
