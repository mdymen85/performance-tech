package com.performance.java;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Data
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int account;
    private BigDecimal balance;

    public AccountEntity() {

    }

    @Builder
    public AccountEntity(int account, BigDecimal balance) {
        this.account = account;
        this.balance = balance;
    }
}
