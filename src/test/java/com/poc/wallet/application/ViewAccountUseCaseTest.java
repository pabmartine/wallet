package com.poc.wallet.application;

import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.Customer;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.ViewAccountUseCase;
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
public class ViewAccountUseCaseTest {

  private static final String IBAN = "ES7921000813610123456789";

  @Autowired
  ViewAccountUseCase viewAccountUseCase;

  @MockBean
  TransactionRepository transactionRepository;

  @MockBean
  AccountRepository accountRepository;

  @MockBean
  Customer customer;

  @MockBean
  Account account;

  @Test
  void given_non_existing_iban_When_execute_then_throw_exception() throws CustomException {

    Mockito.when(accountRepository.findByIban(IBAN)).thenReturn(null);

    CustomException customException = assertThrows(CustomException.class,
        () -> viewAccountUseCase.execute(IBAN));

    Mockito.verify(accountRepository).findByIban(IBAN);
    
    assertEquals("Account not found", customException.getMessage());

  }

  @Test
  void given_an_existing_NIF_When_execute_then_return_account() throws CustomException {

    Mockito.when(accountRepository.findByIban(IBAN)).thenReturn(account);
    Mockito.doNothing().when(accountRepository).save(account);

    Account result = viewAccountUseCase.execute(IBAN);

    Mockito.verify(transactionRepository).findAllByIban(IBAN);
    Mockito.verify(accountRepository).findByIban(IBAN);

    assertEquals(account, result);

  }
}
