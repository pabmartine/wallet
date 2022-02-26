package com.poc.wallet.adapters.out.h2.services;

import java.util.Arrays;
import java.util.List;

import com.poc.wallet.adapters.out.h2.entities.AccountEntity;
import com.poc.wallet.adapters.out.h2.mappers.AccountEntityMapper;
import com.poc.wallet.adapters.out.h2.repositories.AccountJpaRepository;
import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.out.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountRepositoryImplTest {

  private static final String NIF = "00000000T";

  private static final String IBAN = "ES7921000813610123456789";

  @Autowired
  AccountRepository accountRepository;

  @MockBean
  AccountJpaRepository accountJpaRepository;

  @MockBean
  AccountEntityMapper accountMapper;

  @MockBean
  Account account;

  @MockBean
  AccountEntity accountEntity;

  @Test
  public void given_nif_When_findAllByCustomer_then_return_accounts() throws CustomException {

    Mockito.when(accountMapper.entitiesToDomains(Arrays.asList(accountEntity))).thenReturn(Arrays.asList(account));
    Mockito.when(accountJpaRepository.findAllByCustomer(Mockito.anyString())).thenReturn(Arrays.asList(accountEntity));

    List<Account> result = accountRepository.findAllByCustomer(Mockito.anyString());

    Mockito.verify(accountMapper).entitiesToDomains(Arrays.asList(accountEntity));
    Mockito.verify(accountJpaRepository).findAllByCustomer(Mockito.anyString());

    assertEquals(Arrays.asList(account), result);

  }

  @Test
  public void given_iban_When_findByIban_then_return_account() throws CustomException {

    Mockito.when(accountMapper.entityToDomain(accountEntity)).thenReturn(account);
    Mockito.when(accountJpaRepository.findByIban(Mockito.anyString())).thenReturn(accountEntity);

    Account result = accountRepository.findByIban(Mockito.anyString());

    Mockito.verify(accountMapper).entityToDomain(accountEntity);
    Mockito.verify(accountJpaRepository).findByIban(Mockito.anyString());

    assertEquals(account, result);

  }

  @Test
  public void given_non_existing_account_When_save_then_verify() throws CustomException {

    Mockito.when(account.getIban()).thenReturn(IBAN);
    Mockito.when(accountMapper.domainToEntity(account)).thenReturn(accountEntity);
    Mockito.when(accountJpaRepository.findByIban(account.getIban())).thenReturn(null);
    Mockito.when(accountJpaRepository.save(accountEntity)).thenReturn(accountEntity);

    accountRepository.save(NIF, account);

    Mockito.verify(accountMapper).domainToEntity(account);
    Mockito.verify(accountJpaRepository).save(accountEntity);

  }

  @Test
  public void given_existing_account_When_save_then_verify() throws CustomException {

    Mockito.when(account.getIban()).thenReturn(IBAN);
    Mockito.when(accountMapper.domainToEntity(account)).thenReturn(accountEntity);
    Mockito.when(accountJpaRepository.findByIban(account.getIban())).thenReturn(accountEntity);
    Mockito.when(accountJpaRepository.save(accountEntity)).thenReturn(accountEntity);

    accountRepository.save(NIF, account);

    Mockito.verify(accountJpaRepository).save(accountEntity);

  }

}
