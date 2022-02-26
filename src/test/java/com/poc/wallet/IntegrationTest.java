package com.poc.wallet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

  private static final String IBAN_1 = "AL472121100900000002356987411";

  private static final String IBAN_2 = "ES7921000813610123456789";

  private static final String NIF = "00000000T";

  private static final String NAME = "Nombre";

  private static final String SURNAME = "Apellido";

  private static final String BALANCE = "0";

  private static final String AMMOUNT = "100";

  private static final String AMMOUNT_2 = "50";

  @Autowired
  private MockMvc mockMvc;

  @Test
  void doSomething() throws Exception {

    // Registro usuario
    String customer = "{"
        + "\"nif\": \"" + NIF + "\","
        + "\"name\": \"" + NAME + "\","
        + "\"surname\": \"" + SURNAME + "\""
        + "}";

    mockMvc.perform(post("/rest/customer/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(customer))
        .andExpect(status().isOk());

    // Creación de cuenta 1
    String account = "{"
        + "\"iban\": \"" + IBAN_1 + "\","
        + "\"balance\": \"" + BALANCE + "\""
        + "}";

    mockMvc.perform(post("/rest/account/add/" + NIF)
        .contentType(MediaType.APPLICATION_JSON)
        .content(account))
        .andExpect(status().isOk());

    // Visualización de cuenta 1 (wallet) --> Balance (0) y movimientos (0)

    mockMvc.perform(get("/rest/account/view/" + IBAN_1)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.iban", is(String.valueOf(IBAN_1))))
        .andExpect(jsonPath("$.balance", is(Double.valueOf(BALANCE))))
        .andExpect(jsonPath("$.transactions.length()", is(Integer.valueOf(0))));

    // Creación de cuenta 2
    String account2 = "{"
        + "\"iban\": \"" + IBAN_2 + "\","
        + "\"balance\": \"" + BALANCE + "\""
        + "}";

    mockMvc.perform(post("/rest/account/add/" + NIF)
        .contentType(MediaType.APPLICATION_JSON)
        .content(account2))
        .andExpect(status().isOk());

    // Visualización de cuenta 2 (wallet) --> Balance (0) y movimientos (0)

    mockMvc.perform(get("/rest/account/view/" + IBAN_2)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.iban", is(String.valueOf(IBAN_2))))
        .andExpect(jsonPath("$.balance", is(Double.valueOf(BALANCE))))
        .andExpect(jsonPath("$.transactions.length()", is(Integer.valueOf(0))));

    // Realización de depósito de dinero en cuenta 1

    mockMvc.perform(post("/rest/transaction/deposit/" + NIF)
        .contentType(MediaType.APPLICATION_JSON)
        .param("iban", IBAN_1)
        .param("ammount", AMMOUNT))
        .andExpect(status().isOk());

    // Visualización de cuenta 1 (wallet) --> Balance (100) y movimientos (1)

    mockMvc.perform(get("/rest/account/view/" + IBAN_1)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.iban", is(String.valueOf(IBAN_1))))
        .andExpect(jsonPath("$.balance", is(Double.valueOf(BALANCE) + Double.valueOf(AMMOUNT))))
        .andExpect(jsonPath("$.transactions.length()", is(Integer.valueOf(1))));

    // Transferencia de una cuenta A a una cuenta B

    mockMvc.perform(post("/rest/transaction/transfer/" + NIF)
        .contentType(MediaType.APPLICATION_JSON)
        .param("source", IBAN_1)
        .param("target", IBAN_2)
        .param("ammount", AMMOUNT_2))
        .andExpect(status().isOk());

    // Visualización de cuenta 1 (wallet) --> Balance (50) y movimientos (2)

    mockMvc.perform(get("/rest/account/view/" + IBAN_1)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.iban", is(String.valueOf(IBAN_1))))
        .andExpect(jsonPath("$.balance", is(Double.valueOf(BALANCE) + Double.valueOf(AMMOUNT) - Double.valueOf(AMMOUNT_2))))
        .andExpect(jsonPath("$.transactions.length()", is(Integer.valueOf(2))));

    // Visualización de cuenta 2 (wallet) --> Balance (50) y movimientos (1)

    mockMvc.perform(get("/rest/account/view/" + IBAN_2)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.iban", is(String.valueOf(IBAN_2))))
        .andExpect(jsonPath("$.balance", is(Double.valueOf(BALANCE) + Double.valueOf(AMMOUNT_2))))
        .andExpect(jsonPath("$.transactions.length()", is(Integer.valueOf(1))));
  }
}
