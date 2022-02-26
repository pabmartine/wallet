package com.poc.wallet.ports.out;

import java.util.List;

import com.poc.wallet.domain.Account;

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
  public List<Account> findAllByCustomer(String nif);

  /**
   * Returns the account found due entry param
   * 
   * @param iban account identifier
   * @return account
   */
  public Account findByIban(String iban);

  /**
   * Inserts a new account
   * @param nif customer
   * @param account to persist
   */
  public void save(String nif, Account account);

}
