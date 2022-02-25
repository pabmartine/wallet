package com.poc.wallet.adapters.out.h2.services;

import java.util.List;

import com.poc.wallet.adapters.out.h2.mappers.AccountEntityMapper;
import com.poc.wallet.adapters.out.h2.repositories.AccountJpaRepository;
import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.out.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

  @Autowired
  private AccountJpaRepository accountRepository;

  @Autowired
  private AccountEntityMapper accountMapper;

  @Override
  public List<Account> findAllByCustomer(String nif) throws CustomException {
    return accountMapper.entitiesToDomains(accountRepository.findAllByCustomer(nif));
  }

  @Override
  public Account findByIban(String iban) throws CustomException {
    return accountMapper.entityToDomain(accountRepository.findByIban(iban));
  }

  @Override
  public void save(Account account) throws CustomException {
    accountRepository.save(accountMapper.domainToEntity(account));
  }

}
