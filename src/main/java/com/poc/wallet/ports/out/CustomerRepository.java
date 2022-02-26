package com.poc.wallet.ports.out;

import com.poc.wallet.domain.Customer;
import com.poc.wallet.domain.exceptions.CustomException;

/**
 * Output port interface. Customers management.
 * @author pabmartine
 *
 */
public interface CustomerRepository {

  /**
   * find caustomer by nif
   * 
   * @param nif customer identifier
   * @return
   */
  public Customer findByNif(String nif) throws CustomException;

  /**
   * add a new customer
   * 
   * @param customer
   */
  public void save(Customer customer) throws CustomException;

}
