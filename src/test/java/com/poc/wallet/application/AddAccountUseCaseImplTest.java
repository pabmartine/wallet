package com.poc.wallet.application;

import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.Customer;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.AddAccountUseCase;
import com.poc.wallet.ports.out.AccountRepository;
import com.poc.wallet.ports.out.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AddAccountUseCaseImplTest {

  private static final String NIF = "00000000T";

  private static final String IBAN = "ES7921000813610123456789";

  @Autowired
  AddAccountUseCase addAccountUseCase;

  @MockBean
  CustomerRepository customerRepository;

  @MockBean
  AccountRepository accountRepository;

  @MockBean
  Customer customer;

  @MockBean
  Account account;

  @Test
  void given_non_existing_NIF_When_execute_then_throw_exception() throws CustomException {

    Mockito.when(customerRepository.findByNif(NIF)).thenReturn(null);
    Account account = new Account(IBAN);

    CustomException customException = assertThrows(CustomException.class,
        () -> addAccountUseCase.execute(NIF, account));

    assertEquals("Customer not found", customException.getMessage());

  }

  @Test
  void given_an_existing_NIF_When_execute_then_call_save() throws CustomException {

    Mockito.when(customerRepository.findByNif(NIF)).thenReturn(customer);
    Mockito.doNothing().when(accountRepository).save(account);

    addAccountUseCase.execute(NIF, account);

    Mockito.verify(accountRepository).save(account);

  }
}
