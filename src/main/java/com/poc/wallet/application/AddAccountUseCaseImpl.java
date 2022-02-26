package com.poc.wallet.application;

import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.Customer;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.AddAccountUseCase;
import com.poc.wallet.ports.out.AccountRepository;
import com.poc.wallet.ports.out.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Add Account use case implementation
 * 
 * @author pabmartine
 *
 */
@Slf4j
@Service
public class AddAccountUseCaseImpl implements AddAccountUseCase {

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  AccountRepository accountRepository;

  @Override
  public void execute(String nif, Account account) throws CustomException {

    Customer c = customerRepository.findByNif(nif);

    if (c != null) {
      Account a = accountRepository.findByIban(account.getIban());
      if (a == null) {
        accountRepository.save(nif, account);
        log.info("Account saved for customer %d", nif);
      } else {
        throw new CustomException("Account already exist");
      }
    } else {
      throw new CustomException("Customer not found");
    }
  }

}
