package com.poc.wallet.adapters.out.h2.services;

import java.util.List;

import com.poc.wallet.adapters.out.h2.mappers.TransactionEntityMapper;
import com.poc.wallet.adapters.out.h2.repositories.TransactionJpaRepository;
import com.poc.wallet.domain.Transaction;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.out.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

  @Autowired
  private TransactionJpaRepository transactionRepository;

  @Autowired
  private TransactionEntityMapper transactionMapper;

  @Override
  public List<Transaction> findAllByIban(String iban) throws CustomException {
    return transactionMapper.entitiesToDomains(transactionRepository.findAllByIban(iban));
  }

  @Override
  public void save(Transaction transaction) throws CustomException {
    transactionRepository.save(transactionMapper.domainToEntity(transaction));
  }

}
