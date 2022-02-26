package com.poc.wallet.adapters.out.h2.services;

import java.util.Arrays;
import java.util.List;

import com.poc.wallet.adapters.out.h2.entities.TransactionEntity;
import com.poc.wallet.adapters.out.h2.mappers.TransactionEntityMapper;
import com.poc.wallet.adapters.out.h2.repositories.TransactionJpaRepository;
import com.poc.wallet.domain.Transaction;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.out.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransactionRepositoryImplTest {

  @Autowired
  TransactionRepository transactionRepository;

  @MockBean
  TransactionJpaRepository transactionJpaRepository;

  @MockBean
  TransactionEntityMapper transactionMapper;

  @MockBean
  Transaction transaction;

  @MockBean
  TransactionEntity transactionEntity;

  @Test
  public void given_nif_When_findAllByIban_then_return_transactions() throws CustomException {

    Mockito.when(transactionMapper.entitiesToDomains(Arrays.asList(transactionEntity))).thenReturn(Arrays.asList(transaction));
    Mockito.when(transactionJpaRepository.findAllByIban(Mockito.anyString())).thenReturn(Arrays.asList(transactionEntity));

    List<Transaction> result = transactionRepository.findAllByIban(Mockito.anyString());

    Mockito.verify(transactionMapper).entitiesToDomains(Arrays.asList(transactionEntity));
    Mockito.verify(transactionJpaRepository).findAllByIban(Mockito.anyString());

    assertEquals(Arrays.asList(transaction), result);

  }

  @Test
  public void given_transaction_When_save_then_verify() throws CustomException {

    Mockito.when(transactionMapper.domainToEntity(transaction)).thenReturn(transactionEntity);
    Mockito.when(transactionJpaRepository.save(transactionEntity)).thenReturn(transactionEntity);

    transactionRepository.save(Mockito.anyString(), transaction);

    Mockito.verify(transactionMapper).domainToEntity(transaction);
    Mockito.verify(transactionJpaRepository).save(transactionEntity);

  }

}
