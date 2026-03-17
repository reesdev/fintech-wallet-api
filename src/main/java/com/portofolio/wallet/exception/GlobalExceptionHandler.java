package com.portofolio.wallet.exception;

import com.portofolio.wallet.dto.response.CommonResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<CommonResponse<Object>> handleApiException(ApiException ex) {
        CommonResponse<Object> response = CommonResponse.builder()
                .timestamp(LocalDateTime.now())
                .status("ERROR")
                .code("BAD_REQUEST")
                .message(ex.getMessage())
                .data(null)
                .build();

        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Object>> handleGeneralException(ApiException ex) {
        CommonResponse<Object> response = CommonResponse.builder()
                .timestamp(LocalDateTime.now())
                .status("ERROR")
                .code("Internal Server Error")
                .message(ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.internalServerError().body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<CommonResponse<Object>> handleValidationException(MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");
        CommonResponse<Object> response = CommonResponse.builder()
                .timestamp(LocalDateTime.now())
                .status("ERROR")
                .code("VALIDATION_ERROR")
                .message(errorMessage)
                .data(null)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

}
