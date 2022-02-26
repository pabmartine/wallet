package com.poc.wallet.application;

import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.ViewAccountUseCase;
import com.poc.wallet.ports.out.AccountRepository;
import com.poc.wallet.ports.out.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Add Account use case implementation
 * 
 * @author pabmartine
 *
 */
@Slf4j
@Service
public class ViewAccountUseCaseImpl implements ViewAccountUseCase {

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  AccountRepository accountRepository;

  @Override
  public Account execute(String iban) throws CustomException {
    Account account = accountRepository.findByIban(iban);
    if (account == null) {
      throw new CustomException("Account not found");
    }
    if (CollectionUtils.isEmpty(account.getTransactions())) {
      account.setTransactions(transactionRepository.findAllByIban(iban));
    }
    if (CollectionUtils.isEmpty(account.getTransactions())) {
      log.warn("There are no transactions for the iban %d", iban);
    }
    return account;
  }

}
