package com.poc.wallet.adapters.in.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.poc.wallet.adapters.in.rest.mappers.TransactionDtoMapper;
import com.poc.wallet.domain.Transaction;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.DepositTransactionUseCase;
import com.poc.wallet.ports.in.TransferTransactionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionRestControllerTest {

  private static final String NIF = "00000000T";

  private static final String IBAN = "ES7921000813610123456789";

  private static final String IBAN2 = "AL472121100900000002356987411";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AccountRestController accountRestController;

  @Autowired
  private TransactionDtoMapper transactionDtoMapper;

  @MockBean
  private TransferTransactionUseCase transferTransactionUseCase;

  @MockBean
  private DepositTransactionUseCase depositTransactionUseCase;

  @BeforeEach
  void init() throws CustomException, JsonMappingException, JsonProcessingException {

    // Mockito.when(viewAccountUseCase.execute(IBAN2)).thenThrow(CustomException.class);
    // Mockito.when(depositTransactionUseCase.execute(IBAN)).thenReturn(account);
  }

  @Test
  public void given_valid_params_When_deposit_then_return_ok() throws Exception {

    Transaction transaction = new Transaction();
    transaction.deposit(IBAN, 100);
    Mockito.doNothing().when(depositTransactionUseCase).execute(NIF, transaction);

    mockMvc.perform(post("/rest/transaction/deposit/" + NIF)
        .contentType(MediaType.APPLICATION_JSON)
        .param("iban", "1")
        .param("ammount", "100"))
        .andExpect(status().isOk());

  }

  @Test
  public void given_not_valid_params_When_deposit_then_return_internalservererror() throws Exception {

    mockMvc.perform(post("/rest/transaction/deposit/" + NIF)
        .contentType(MediaType.APPLICATION_JSON)
        .param("iban", "1"))
        .andExpect(status().isInternalServerError());
  }

  @Test
  public void given_valid_params_When_transfer_then_return_ok() throws Exception {

    Transaction transaction = new Transaction();
    transaction.deposit(IBAN, 100);
    Mockito.doNothing().when(depositTransactionUseCase).execute(NIF, transaction);

    mockMvc.perform(post("/rest/transaction/transfer/" + NIF)
        .contentType(MediaType.APPLICATION_JSON)
        .param("source", IBAN)
        .param("target", IBAN2)
        .param("ammount", "100"))
        .andExpect(status().isOk());

  }

  @Test
  public void given_not_valid_params_When_transfer_then_return_internalservererror() throws Exception {

    mockMvc.perform(post("/rest/transaction/transfer/" + NIF)
        .contentType(MediaType.APPLICATION_JSON)
        .param("iban", "1"))
        .andExpect(status().isInternalServerError());
  }

}
