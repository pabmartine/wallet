package com.poc.wallet.adapters.out.h2;

import java.util.List;

import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.out.AccountRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

  @Override
  public List<Account> findAllByCustomer(String nif) throws CustomException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Account findByIban(String iban) throws CustomException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void save(Account account) throws CustomException {
    // TODO Auto-generated method stub

  }

}
