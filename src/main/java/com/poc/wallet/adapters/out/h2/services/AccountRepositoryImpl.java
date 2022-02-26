package com.poc.wallet.adapters.out.h2.services;

import java.util.List;

import com.poc.wallet.adapters.out.h2.entities.AccountEntity;
import com.poc.wallet.adapters.out.h2.mappers.AccountEntityMapper;
import com.poc.wallet.adapters.out.h2.repositories.AccountJpaRepository;
import com.poc.wallet.adapters.out.h2.repositories.CustomerJpaRepository;
import com.poc.wallet.domain.Account;
import com.poc.wallet.ports.out.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

  @Autowired
  private AccountJpaRepository accountJpaRepository;

  @Autowired
  private CustomerJpaRepository customerRepository;

  @Autowired
  private AccountEntityMapper accountMapper;

  @Override
  public List<Account> findAllByCustomer(String nif) {
    return accountMapper.entitiesToDomains(accountJpaRepository.findAllByCustomer(nif));
  }

  @Override
  public Account findByIban(String iban) {
    return accountMapper.entityToDomain(accountJpaRepository.findByIban(iban));
  }

  @Override
  public void save(String nif, Account account) {
    AccountEntity accountEntity = accountJpaRepository.findByIban(account.getIban());
    if (accountEntity == null) {
      accountEntity = accountMapper.domainToEntity(account);
      accountEntity.setCustomer(customerRepository.findByNif(nif));
    } else {
      accountEntity.setBalance(account.getBalance());
    }
    accountJpaRepository.save(accountEntity);
  }

}
