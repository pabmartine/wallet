package com.poc.wallet.ports.out;

import java.util.List;

import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.exceptions.CustomException;

/**
 * Output port interface. Accounts management.
 * @author pabmartine
 *
 */
public interface AccountRepository {

  /**
   * Returns all accounts found due entry param
   * 
   * @param nif customer identifier
   * @return list of accounts
   */
  public List<Account> findAllByCustomer(String nif) throws CustomException;

  /**
   * Returns the account found due entry param
   * 
   * @param iban account identifier
   * @return account
   */
  public Account findByIban(String iban) throws CustomException;

  /**
   * Inserts a new account
   * @param account to persist
   */
  public void save(Account account) throws CustomException;

}
