package com.inditex.prices.service.infrastructure.entrypoint.rest;

import com.inditex.prices.service.domain.exceptions.InvalidDateFormatException;
import com.inditex.prices.service.domain.exceptions.PriceNotFoundException;
import org.openapitools.model.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlePriceNotFound(PriceNotFoundException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(404).body(errorResponse); // 404 Not Found
    }

    @ExceptionHandler(InvalidDateFormatException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidDateFormat(InvalidDateFormatException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(400).body(errorResponse); // 400 Bad Request
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setMessage("An unexpected error occurred: " + ex.getMessage());
        return ResponseEntity.status(500).body(errorResponse); // 500 Internal Server Error
    }
}