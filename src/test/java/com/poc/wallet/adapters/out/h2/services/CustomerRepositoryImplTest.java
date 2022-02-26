package com.poc.wallet.adapters.out.h2.services;

import com.poc.wallet.adapters.out.h2.entities.CustomerEntity;
import com.poc.wallet.adapters.out.h2.mappers.CustomerEntityMapper;
import com.poc.wallet.adapters.out.h2.repositories.CustomerJpaRepository;
import com.poc.wallet.domain.Customer;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.out.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerRepositoryImplTest {

  @Autowired
  CustomerRepository customerRepository;

  @MockBean
  CustomerJpaRepository customerJpaRepository;

  @MockBean
  CustomerEntityMapper customerMapper;

  @MockBean
  Customer customer;

  @MockBean
  CustomerEntity customerEntity;

  @Test
  public void given_nif_When_findByNif_then_return_customer() throws CustomException {

    Mockito.when(customerMapper.entityToDomain(customerEntity)).thenReturn(customer);
    Mockito.when(customerJpaRepository.findByNif(Mockito.anyString())).thenReturn(customerEntity);

    Customer result = customerRepository.findByNif(Mockito.anyString());

    Mockito.verify(customerMapper).entityToDomain(customerEntity);
    Mockito.verify(customerJpaRepository).findByNif(Mockito.anyString());

    assertEquals(customer, result);

  }

  @Test
  public void given_customer_When_save_then_verify() throws CustomException {

    Mockito.when(customerMapper.domainToEntity(customer)).thenReturn(customerEntity);
    Mockito.when(customerJpaRepository.save(customerEntity)).thenReturn(customerEntity);

    customerRepository.save(customer);

    Mockito.verify(customerMapper).domainToEntity(customer);
    Mockito.verify(customerJpaRepository).save(customerEntity);

  }

}
