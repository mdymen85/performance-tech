package com.performance.java;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @RequestMapping(path = "/v1/account", method = RequestMethod.POST)
    public ResponseEntity<Void> createAccount(@RequestBody AccountRequest request) {
        balanceService.createAccount(request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(path = "/v1/transaction", method = RequestMethod.POST)
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionRequest request) {
        balanceService.createTransaction(request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(path = "/v2/account", method = RequestMethod.POST)
    public ResponseEntity<Void> createAccountJdbc(@RequestBody AccountRequest request) {
        balanceService.createAccount(request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(path = "/v2/transaction", method = RequestMethod.POST)
    public ResponseEntity<Void> createTransactionJdbc(@RequestBody TransactionRequest request) {
        balanceService.createTransactionJdbc(request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
