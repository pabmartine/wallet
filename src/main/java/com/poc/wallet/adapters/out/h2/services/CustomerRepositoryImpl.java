package com.poc.wallet.adapters.out.h2.services;

import com.poc.wallet.adapters.out.h2.mappers.CustomerEntityMapper;
import com.poc.wallet.adapters.out.h2.repositories.CustomerJpaRepository;
import com.poc.wallet.domain.Customer;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.out.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

  @Autowired
  private CustomerJpaRepository customerRepository;

  @Autowired
  private CustomerEntityMapper customerMapper;

  @Override
  public Customer findByNif(String nif) throws CustomException {
    return customerMapper.entityToDomain(customerRepository.findByNif(nif));
  }

  @Override
  public void save(Customer customer) throws CustomException {
    customerRepository.save(customerMapper.domainToEntity(customer));
  }

}
