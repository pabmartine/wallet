package com.poc.wallet.ports.in;

import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.exceptions.CustomException;

/**
 * Input port interface. Use case for view account balance and transations
 * @author pabmartine
 *
 */
public interface ViewAccountUseCase {

  public Account execute(String iban) throws CustomException;

}
