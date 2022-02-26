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
import static org.mockito.Mockito.times;

@SpringBootTest
public class TransferTransactionUseCaseImplTest {

  private static final String IBAN = "AL472121100900000002356987411";
  private static final String NIF = "00000000t";

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
        () -> transferTransactionUseCase.execute(NIF, transaction));

    assertEquals("Source account does not exist", customException.getMessage());
  }

  @Test
  void given_a_non_existing_target_account_When_execute_then_throw_exception() throws CustomException {

    Mockito.when(transaction.getSource()).thenReturn(IBAN);
    Mockito.when(transaction.getTarget()).thenReturn(IBAN);
    Mockito.when(accountRepository.findByIban(IBAN)).thenReturn(account).thenReturn(null);

    CustomException customException = assertThrows(CustomException.class,
        () -> transferTransactionUseCase.execute(NIF, transaction));

    assertEquals("Target account does not exist", customException.getMessage());
  }

  @Test
  void given_existing_iban_When_execute_then_return_call_save() throws CustomException {

    Mockito.when(transaction.getSource()).thenReturn(IBAN);
    Mockito.when(transaction.getTarget()).thenReturn(IBAN);
    Mockito.when(account.getIban()).thenReturn(IBAN);
    Mockito.when(accountRepository.findByIban(IBAN)).thenReturn(account).thenReturn(account);
    Mockito.doNothing().when(transactionRepository).save(Mockito.anyString(), Mockito.any());
    Mockito.doNothing().when(accountRepository).save(NIF, account);

    transferTransactionUseCase.execute(NIF, transaction);

    Mockito.verify(transactionRepository, times(2)).save(Mockito.anyString(), Mockito.any());
    Mockito.verify(accountRepository, times(2)).save(NIF, account);
  }
}
