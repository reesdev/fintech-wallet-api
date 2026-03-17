package com.portofolio.wallet.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {
    @NotNull
    @Positive
    private BigDecimal amount;
}
