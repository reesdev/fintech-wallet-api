package com.portofolio.wallet.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private Long receiverId;
    private BigDecimal amount;
    private String referenceId;
}
