package com.poc.wallet.adapters.in.rest.controllers;

import com.poc.wallet.adapters.in.rest.mappers.TransactionDtoMapper;
import com.poc.wallet.domain.Transaction;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.DepositTransactionUseCase;
import com.poc.wallet.ports.in.TransferTransactionUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controllers class that receives client requests
 * @author pmarti14
 *
 */
@Slf4j
@RestController
@RequestMapping("/rest/transaction")
public class TransactionRestController {

  @Autowired
  private TransferTransactionUseCase transferTransactionUseCase;

  @Autowired
  private DepositTransactionUseCase depositTransactionUseCase;

  @Autowired
  protected TransactionDtoMapper transactionDtoMapper;

  @ResponseStatus(HttpStatus.OK)
  @PostMapping(value = "/deposit/{nif}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deposit(@PathVariable String nif, @RequestParam String iban, @RequestParam double ammount) throws CustomException {
    Transaction transaction = new Transaction();
    transaction.deposit(iban, ammount);
    depositTransactionUseCase.execute(nif, transaction);
    log.info("Transaction %d created", transaction.toString());
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @ResponseStatus(HttpStatus.OK)
  @PostMapping(value = "/transfer/{nif}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> transfer(@PathVariable String nif, @RequestParam String source, @RequestParam String target, @RequestParam double ammount) throws CustomException {
    Transaction transaction = new Transaction();
    transaction.transfer(source, target, ammount);
    transferTransactionUseCase.execute(nif, transaction);
    log.info("Transaction %d created", transaction.toString());
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
