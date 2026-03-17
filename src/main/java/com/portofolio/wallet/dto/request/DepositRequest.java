package com.portofolio.wallet.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {
    @NotNull
    @Positive
    @DecimalMin(value = "1.0",message = "Amount must be greater than 0")
    private BigDecimal amount;
}
