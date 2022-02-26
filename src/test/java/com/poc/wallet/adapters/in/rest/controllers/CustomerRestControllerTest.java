package com.poc.wallet.adapters.in.rest.controllers;

import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.wallet.adapters.in.rest.dto.CustomerDto;
import com.poc.wallet.adapters.in.rest.mappers.CustomerDtoMapper;
import com.poc.wallet.domain.Customer;
import com.poc.wallet.domain.exceptions.CustomException;
import com.poc.wallet.ports.in.AddCustomerUseCase;
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
public class CustomerRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  CustomerRestController customerRestController;

  @Autowired
  CustomerDtoMapper customerDtoMapper;

  @MockBean
  private AddCustomerUseCase addCustomerUseCase;

  @BeforeEach
  void init() throws CustomException, JsonMappingException, JsonProcessingException {

    String json = "{"
        + "\"nif\": \"1\","
        + "\"name\": \"2\","
        + "\"surname\": \"3\""
        + "}";

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
    CustomerDto customerDto = objectMapper.readValue(json, CustomerDto.class);
    Customer customer = customerDtoMapper.dtoToDomain(customerDto);

    Mockito.doNothing().when(addCustomerUseCase).execute(customer);

  }

  @Test
  public void given_valid_json_When_add_then_return_ok() throws Exception {

    String json = "{"
        + "\"nif\": \"1\","
        + "\"name\": \"2\","
        + "\"surname\": \"3\""
        + "}";

    mockMvc.perform(post("/rest/customer/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk());
  }

  @Test
  public void given_not_valid_json_When_add_then_return_badrequest() throws Exception {

    String json = "{"
        + "\"nif\": \"1\","
        + "\"name\": \"2\""
        + "}";

    mockMvc.perform(post("/rest/customer/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isBadRequest());
  }

}
