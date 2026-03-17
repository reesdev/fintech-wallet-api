package com.portofolio.wallet.exception;

import com.portofolio.wallet.dto.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

}
