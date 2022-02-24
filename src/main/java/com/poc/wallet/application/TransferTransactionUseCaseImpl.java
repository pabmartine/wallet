package com.poc.wallet.application;

import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.Transaction;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.TransferTransactionUseCase;
import com.poc.wallet.ports.out.AccountRepository;
import com.poc.wallet.ports.out.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Transfer transaction use case implementation
 * @author pabmartine
 *
 */
@Slf4j
@Service
public class TransferTransactionUseCaseImpl implements TransferTransactionUseCase {

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  TransactionRepository transactionRepository;

  @Override
  public void execute(Transaction transaction) throws CustomException {

    Account source = accountRepository.findByIban(transaction.getSource());
    if (source == null)
      throw new CustomException("Source account does not exist");

    Account target = accountRepository.findByIban(transaction.getTarget());
    if (target == null)
      throw new CustomException("Target account does not exist");

    transactionRepository.save(transaction);
    log.info("Transaction saved for account %d", transaction.getSource());
  }

}
