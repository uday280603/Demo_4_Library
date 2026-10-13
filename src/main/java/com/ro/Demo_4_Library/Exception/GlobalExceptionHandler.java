package com.ro.Demo_4_Library.Exception;



import com.ro.Demo_4_Library.Response.ApiResponse;
import jakarta.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle all DTO validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleValidationException(
            MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": "
                        + error.getDefaultMessage())
                .distinct()
                .toList();

        ApiResponse<List<String>> response = new ApiResponse<>(
                "Validation failed",
                errors,
                false
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // Handle resource or URL not found
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFound(
            NoResourceFoundException ex) {

        ApiResponse<String> response = new ApiResponse<>(
                "Resource not found",
                ex.getMessage(),
                false
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    // Handle passenger not found
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> handleRuntimeException(
            RuntimeException ex) {

        ApiResponse<String> response = new ApiResponse<>(
                ex.getMessage(),
                null,
                false
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    // Handle database constraint errors
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleDatabaseError(
            DataIntegrityViolationException ex) {

        ApiResponse<String> response = new ApiResponse<>(
                "Database validation failed",
                "Check required fields, duplicate values, or database constraints",
                false
        );

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(response);
    }

    // Handle constraint violations
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleConstraintViolation(
            ConstraintViolationException ex) {

        ApiResponse<String> response = new ApiResponse<>(
                "Validation failed",
                ex.getMessage(),
                false
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // Handle all other unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(
            Exception ex) {

        ApiResponse<String> response = new ApiResponse<>(
                "Internal server error",
                "Something went wrong",
                false
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
