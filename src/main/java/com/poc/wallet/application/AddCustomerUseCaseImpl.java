package com.poc.wallet.application;

import com.poc.wallet.domain.Customer;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.AddCustomerUseCase;
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
public class AddCustomerUseCaseImpl implements AddCustomerUseCase {

  @Autowired
  CustomerRepository customerRepository;

  @Override
  public void execute(Customer customer) throws CustomException {

    Customer c = customerRepository.findByNif(customer.getNif());

    if (c == null) {
      customerRepository.save(customer);
      log.info("Customer %d successfully", customer.getNif());
    } else {
      throw new CustomException("Customer already exists");
    }
  }

}
