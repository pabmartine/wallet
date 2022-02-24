package com.poc.wallet.application;

import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.Transaction;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.TransferTransactionUseCase;
import com.poc.wallet.ports.out.AccountRepository;
import com.poc.wallet.ports.out.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TransferTransactionUseCaseTest {

  private static final String IBAN = "AL472121100900000002356987411";

  @Autowired
  TransferTransactionUseCase transferTransactionUseCase;

  @MockBean
  TransactionRepository transactionRepository;

  @MockBean
  AccountRepository accountRepository;

  @MockBean
  Transaction transaction;

  @MockBean
  Account account;

  @Test
  void given_a_non_existing_source_account_When_execute_then_throw_exception() throws CustomException {

    Mockito.when(transaction.getSource()).thenReturn(IBAN);
    Mockito.when(accountRepository.findByIban(IBAN)).thenReturn(null);

    CustomException customException = assertThrows(CustomException.class,
        () -> transferTransactionUseCase.execute(transaction));

    assertEquals("Source account does not exist", customException.getMessage());
  }

  @Test
  void given_a_non_existing_target_account_When_execute_then_throw_exception() throws CustomException {

    Mockito.when(transaction.getSource()).thenReturn(IBAN);
    Mockito.when(transaction.getTarget()).thenReturn(IBAN);
    Mockito.when(accountRepository.findByIban(IBAN)).thenReturn(account).thenReturn(null);

    CustomException customException = assertThrows(CustomException.class,
        () -> transferTransactionUseCase.execute(transaction));

    assertEquals("Target account does not exist", customException.getMessage());
  }

  @Test
  void given_existing_ibansWhen_execute_then_return_call_save() throws CustomException {

    Mockito.when(transaction.getSource()).thenReturn(IBAN);
    Mockito.when(transaction.getTarget()).thenReturn(IBAN);
    Mockito.when(accountRepository.findByIban(IBAN)).thenReturn(account).thenReturn(account);
    Mockito.doNothing().when(transactionRepository).save(transaction);

    transferTransactionUseCase.execute(transaction);

    Mockito.verify(transactionRepository).save(transaction);
  }
}
