package com.krillinator.demo_5.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;

@RestControllerAdvice
public class AdviceValidationHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ValidationErrorResponse> handleGlobalRestValidation(WebExchangeBindException exception) {

        List<ApiErrorResponseDTO> errors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new ApiErrorResponseDTO(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .toList();

        return ResponseEntity.badRequest().body(
                new ValidationErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Validation Failed",
                        errors
                )
        );
    }

}
