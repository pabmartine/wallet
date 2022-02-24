package com.poc.wallet.adapters.out.h2;

import java.util.List;

import com.poc.wallet.domain.Transaction;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.out.TransactionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

  @Override
  public List<Transaction> findAllByIban(String iban) throws CustomException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void save(Transaction transaction) throws CustomException {
    // TODO Auto-generated method stub

  }

}
