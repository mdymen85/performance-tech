package com.performance.java;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

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
}
