package org.ecommerce.authservice.advices;

import org.ecommerce.authservice.dtos.responses.BaseResponse;
import org.ecommerce.authservice.dtos.responses.ErrorResponse;
import org.ecommerce.authservice.exceptions.UserAlreadyExistsException;
import org.ecommerce.authservice.exceptions.UserNotFoundException;
import org.ecommerce.authservice.exceptions.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setDetails(e.getLocalizedMessage());
        return new ResponseEntity<>(
                BaseResponse.failure(response, "Request failed."),
                HttpStatus.CONFLICT
        );
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setDetails(e.getLocalizedMessage());
        return new ResponseEntity<>(
                BaseResponse.failure(response, "Request failed."),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleWrongPasswordException(WrongPasswordException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setDetails(e.getLocalizedMessage());
        return new ResponseEntity<>(
                BaseResponse.failure(response, "Request failed."),
                HttpStatus.BAD_REQUEST
        );
    }
}
