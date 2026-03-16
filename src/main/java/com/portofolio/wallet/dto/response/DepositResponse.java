package com.portofolio.wallet.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositResponse {
    private BigDecimal balance;
}
