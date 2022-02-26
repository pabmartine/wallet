package com.poc.wallet.ports.in;

import com.poc.wallet.domain.Transaction;
import com.poc.wallet.domain.exceptions.CustomException;

/**
 * Input port interface. Use case for transfer transactions
 * @author pabmartine
 *
 */
public interface TransferTransactionUseCase {

  public void execute(String nif, Transaction transaction) throws CustomException;

}
