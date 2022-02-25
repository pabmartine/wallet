package com.poc.wallet.application;

import com.poc.wallet.domain.Customer;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.AddCustomerUseCase;
import com.poc.wallet.ports.out.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AddCustomerUseCaseImplTest {

  private static final String NIF = "00000000T";

  @Autowired
  AddCustomerUseCase addCustomerUseCase;

  @MockBean
  CustomerRepository customerRepository;

  @MockBean
  Customer customer;

  @Test
  void given_an_existing_NIF_When_execute_then_throw_exception() throws CustomException {

    Mockito.when(customer.getNif()).thenReturn(NIF);
    Mockito.when(customerRepository.findByNif(customer.getNif())).thenReturn(customer);

    CustomException customException = assertThrows(CustomException.class,
        () -> addCustomerUseCase.execute(customer));

    assertEquals("Customer already exists", customException.getMessage());
  }

  @Test
  void given_a_non_existing_NIF_When_execute_then_return_call_save() throws CustomException {

    Mockito.when(customer.getNif()).thenReturn(NIF);
    Mockito.when(customerRepository.findByNif(customer.getNif())).thenReturn(null);
    Mockito.doNothing().when(customerRepository).save(customer);

    addCustomerUseCase.execute(customer);

    Mockito.verify(customerRepository).save(customer);
  }
}
