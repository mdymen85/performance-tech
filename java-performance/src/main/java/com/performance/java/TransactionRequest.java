package com.performance.java;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    private int account;
    private BigDecimal tvalue;
    private String ttype;

}
