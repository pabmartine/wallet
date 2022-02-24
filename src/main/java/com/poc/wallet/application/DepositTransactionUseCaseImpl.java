package com.poc.wallet.application;

import java.util.List;

import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.Transaction;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.DepositTransactionUseCase;
import com.poc.wallet.ports.out.AccountRepository;
import com.poc.wallet.ports.out.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Deposit transaction use case implementation
 * @author pabmartine
 *
 */
@Slf4j
@Service
public class DepositTransactionUseCaseImpl implements DepositTransactionUseCase {

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  TransactionRepository transactionRepository;

  @Override
  public void execute(Transaction transaction) throws CustomException {

    Account target = accountRepository.findByIban(transaction.getTarget());
    if (target==null)
      throw new CustomException("Target account does not exist");

    transactionRepository.save(transaction);
    log.info("Transaction saved for account %d", transaction.getSource());
  }

}
