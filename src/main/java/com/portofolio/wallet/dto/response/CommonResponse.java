package com.portofolio.wallet.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommonResponse<T> {
    private LocalDateTime timestamp;
    private String status;
    private String code;
    private String message;
    private T data;
}
