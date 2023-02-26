package com.performance.java;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final JdbcTemplate jdbcTemplate;

    public void createAccount(AccountRequest request) {
        accountRepository.save(AccountEntity
                .builder()
                .account(request.getAccount())
                .balance(request.getBalance())
                .build());
    }

    @Transactional
    public void createTransaction(TransactionRequest request) {

        var account = this.accountRepository.findByAccount(request.getAccount())
                .orElseThrow();

        this.transactionRepository.save(TransactionEntity
                .builder()
                .account(request.getAccount())
                .type(request.getTtype())
                .tvalue(request.getTvalue())
                .build());

        account.setBalance(account.getBalance().add(request.getTvalue()));

        accountRepository.save(account);
    }
    
    public void createTransactionJdbc(TransactionRequest request) {
        var account = jdbcTemplate.query("SELECT * FROM accounts WHERE account = " + request.getAccount() + " FOR UPDATE ", new RowMapper<AccountEntity>() {
            @Override
            public AccountEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
               return AccountEntity.builder()
                       .account(rs.getInt("account"))
                       .balance(rs.getBigDecimal("balance"))
                       .build();
            }
        }).get(0);

        jdbcTemplate.execute("INSERT INTO transactions(account, tvalue, created_at, type) VALUES(" + request.getAccount() + ", " + request.getTvalue() + ", CURRENT_TIMESTAMP(), '" + request.getTtype() + "')");

        jdbcTemplate.execute("UPDATE accounts SET balance = balance + " + request.getTvalue() + " WHERE account = " + request.getAccount());
    }
}
