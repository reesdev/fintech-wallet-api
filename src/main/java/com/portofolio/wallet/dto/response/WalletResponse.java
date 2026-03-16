package com.portofolio.wallet.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletResponse {
    private BigDecimal balance;
}
