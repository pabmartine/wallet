package com.poc.wallet.adapters.in.rest.controllers;

import com.poc.wallet.adapters.in.rest.dto.CustomerDto;
import com.poc.wallet.adapters.in.rest.mappers.CustomerDtoMapper;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.AddCustomerUseCase;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controllers class that receives client requests
 * @author pmarti14
 *
 */
@Slf4j
@RestController
@RequestMapping("/rest/customer")
public class CustomerRestController {

  @Autowired
  private AddCustomerUseCase addCustomerUseCase;

  @Autowired
  protected CustomerDtoMapper customerDtoMapper;

  @ResponseStatus(HttpStatus.OK)
  @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> add(@Valid @RequestBody CustomerDto customerDto) throws CustomException {
    addCustomerUseCase.execute(customerDtoMapper.dtoToDomain(customerDto));
    log.info("Customer %d created", customerDto.toString());
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
