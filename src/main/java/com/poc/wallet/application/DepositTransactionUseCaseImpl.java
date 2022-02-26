package com.poc.wallet.application;

import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.Transaction;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.DepositTransactionUseCase;
import com.poc.wallet.ports.out.AccountRepository;
import com.poc.wallet.ports.out.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public void execute(String nif, Transaction transaction) throws CustomException {

    Account account = accountRepository.findByIban(transaction.getTarget());
    if (account == null)
      throw new CustomException("Account does not exist");

    transactionRepository.save(account.getIban(), transaction);

    account.setBalance(account.getBalance() + transaction.getAmmount());
    accountRepository.save(nif, account);

    log.info("Transaction saved for account %d", transaction.getSource());
  }

}
