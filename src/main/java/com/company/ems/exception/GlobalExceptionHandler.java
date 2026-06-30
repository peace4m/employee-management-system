package com.company.ems.exception;

import com.company.ems.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle Authorization errors
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(403).body(new ApiResponse(false, "Forbidden: You do not have permission."));
    }

    // Handle Authentication errors (add to your SecurityConfig)
    // Return: ResponseEntity.status(401).body(new ApiResponse(false, "Unauthorized access."));
}
