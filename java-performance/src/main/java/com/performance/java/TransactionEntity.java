package com.performance.java;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transactions")
@Data
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int account;

    private BigDecimal tvalue;

    @CreationTimestamp
    private Instant createdAt;

    private String type;

    public TransactionEntity() {

    }

    @Builder
    public TransactionEntity(int account, BigDecimal tvalue, String type) {
        this.account = account;
        this.tvalue = tvalue;
        this.type = type;
    }
}
