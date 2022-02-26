package com.poc.wallet.adapters.in.rest.controllers;

import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.wallet.adapters.in.rest.dto.AccountDto;
import com.poc.wallet.adapters.in.rest.mappers.AccountDtoMapper;
import com.poc.wallet.domain.Account;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.AddAccountUseCase;
import com.poc.wallet.ports.in.ViewAccountUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountRestControllerTest {

  private static final String NIF = "00000000T";

  private static final String IBAN = "ES7921000813610123456789";

  private static final String IBAN2 = "AL472121100900000002356987411";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  AccountRestController accountRestController;

  @Autowired
  AccountDtoMapper accountDtoMapper;

  @MockBean
  private AddAccountUseCase addAccountUseCase;

  @MockBean
  private ViewAccountUseCase viewAccountUseCase;

  @MockBean
  private AccountDto accountDto;

  @BeforeEach
  void init() throws CustomException, JsonMappingException, JsonProcessingException {

    String json = "{"
        + "\"iban\": \"AL472121100900000002356987411\","
        + "\"balance\": \"0\""
        + "}";

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
    AccountDto accountDto = objectMapper.readValue(json, AccountDto.class);
    Account account = accountDtoMapper.dtoToDomain(accountDto);

    Mockito.doNothing().when(addAccountUseCase).execute(NIF, account);
    Mockito.when(viewAccountUseCase.execute(IBAN2)).thenThrow(CustomException.class);
    Mockito.when(viewAccountUseCase.execute(IBAN)).thenReturn(account);
  }

  @Test
  public void given_valid_json_When_add_then_return_ok() throws Exception {

    String json = "{"
        + "\"iban\": \"AL472121100900000002356987411\","
        + "\"balance\": \"0\""
        + "}";

    mockMvc.perform(post("/rest/account/add/" + NIF)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk());
  }

  @Test
  public void given_not_valid_json_When_add_then_return_badrequest() throws Exception {

    String json = "{"
        + "\"iban\": \"AL472121100900000002356987411\""
        + "}";

    mockMvc.perform(post("/rest/account/add/" + NIF)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void given_non_existing_iban_When_view_then_return_not_found() throws Exception {

    mockMvc.perform(get("/rest/account/view/" + IBAN2)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void given_existing_iban_When_view_then_return_ok() throws Exception {

    mockMvc.perform(get("/rest/account/view/" + IBAN)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

}
