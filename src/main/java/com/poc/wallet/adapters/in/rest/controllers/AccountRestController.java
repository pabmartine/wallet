package com.poc.wallet.adapters.in.rest.controllers;

import com.poc.wallet.adapters.in.rest.dto.AccountDto;
import com.poc.wallet.adapters.in.rest.mappers.AccountDtoMapper;
import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.AddAccountUseCase;
import com.poc.wallet.ports.in.ViewAccountUseCase;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/rest/account")
public class AccountRestController {

  @Autowired
  private AddAccountUseCase addAccountUseCase;

  @Autowired
  private ViewAccountUseCase viewAccountUseCase;

  @Autowired
  protected AccountDtoMapper accountDtoMapper;

  @ResponseStatus(HttpStatus.OK)
  @PostMapping(value = "/add/{nif}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> add(@PathVariable String nif, @Valid @RequestBody AccountDto accountDto) throws CustomException {
    addAccountUseCase.execute(nif, accountDtoMapper.dtoToDomain(accountDto));
    log.info("Account %d created", accountDto.toString());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/view/{iban}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AccountDto> add(@PathVariable String iban) throws CustomException {
    Account account = viewAccountUseCase.execute(iban);
    AccountDto accountDto = accountDtoMapper.domainToDto(account);
    return new ResponseEntity<>(accountDto, HttpStatus.OK);
  }

}
