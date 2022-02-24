package com.poc.wallet.adapters.out.h2;

import com.poc.wallet.domain.Customer;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.out.CustomerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

  @Override
  public Customer findByNif(String nif) throws CustomException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void save(Customer customer) throws CustomException {
    // TODO Auto-generated method stub

  }

}
