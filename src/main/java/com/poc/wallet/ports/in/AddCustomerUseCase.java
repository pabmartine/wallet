package com.poc.wallet.ports.in;

import com.poc.wallet.domain.Customer;
import com.poc.wallet.domain.exceptions.CustomException;

/**
 * Input port interface. Use case for customers creation
 * @author pabmartine
 *
 */
public interface AddCustomerUseCase {

  public void execute(Customer customer) throws CustomException;

}
