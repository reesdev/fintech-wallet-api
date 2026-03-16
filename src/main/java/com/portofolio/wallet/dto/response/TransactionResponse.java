package com.portofolio.wallet.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private String type;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
