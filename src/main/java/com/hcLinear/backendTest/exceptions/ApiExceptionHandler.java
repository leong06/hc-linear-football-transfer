package com.hcLinear.backendTest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError notFound(NotFoundException exception) {
        return new ApiError("not_found", exception.getMessage(), List.of());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError validationException(ValidationException exception) {
        return new ApiError("validation_error", exception.getMessage(), List.of());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError conflictException(ConflictException exception) {
        return new ApiError("conflict_error", exception.getMessage(), List.of());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError validationError(MethodArgumentNotValidException exception){
        List<String> details = new ArrayList<>();
        for(FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            details.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        return new ApiError("method_not_allowed", "Hibás kérés!", details);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError generalException(Exception exception){
        return new ApiError("internal_server_error", "Váratlan hiba történt!", List.of());
    }





}
