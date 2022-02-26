package com.poc.wallet.ports.in;

import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.exceptions.CustomException;

/**
 * Input port interface. Use case for accounts creation
 * @author pabmartine
 *
 */
public interface AddAccountUseCase {

  public void execute(String nif, Account account) throws CustomException;

}
