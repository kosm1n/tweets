package com.cosmin.tweets.error;

import com.cosmin.tweets.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> errorHandler(Exception exception) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage()).build();

        if (exception instanceof MethodArgumentNotValidException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            List<FieldError> fieldErrorList = ((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors();
            List<String> errors = fieldErrorList.stream().map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            response.setMessage(errors.toString());
        } else if (exception instanceof MissingServletRequestParameterException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(exception.getMessage());
        } else if (exception instanceof NotFoundException) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage(exception.getMessage());
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
