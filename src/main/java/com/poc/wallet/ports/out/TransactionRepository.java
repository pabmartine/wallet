package com.poc.wallet.ports.out;

import java.util.List;

import com.poc.wallet.domain.Transaction;
import com.poc.wallet.domain.exceptions.CustomException;

/**
 * Output port interface. Transactions management.
 * @author pabmartine
 *
 */
public interface TransactionRepository {

  /**
   * Returns all transactions found due entry params
   * 
   * @param iban iban code
   * @return list of transactions
   */
  public List<Transaction> findAllByIban(String iban) throws CustomException;

  /**
   * Inserts a new transaction regardless of its type
   * @param transaction to persist
   */
  public void save(Transaction transaction) throws CustomException;

}
