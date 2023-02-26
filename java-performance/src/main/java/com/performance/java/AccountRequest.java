package com.performance.java;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequest {

    private int account;
    private BigDecimal balance;

}
