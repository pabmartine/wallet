package com.poc.wallet.ports.in;

import com.poc.wallet.domain.Transaction;
import com.poc.wallet.domain.exceptions.CustomException;

/**
 * Input port interface. Use case for deposit transations
 * @author pabmartine
 *
 */
public interface DepositTransactionUseCase {

  public void execute(String nif, Transaction transaction) throws CustomException;

}
