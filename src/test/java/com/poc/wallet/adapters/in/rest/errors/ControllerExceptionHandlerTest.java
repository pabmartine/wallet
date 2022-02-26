package com.poc.wallet.adapters.in.rest.errors;

import com.poc.wallet.domain.exceptions.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ControllerExceptionHandlerTest {

  public final static FieldError FIELD_ERROR = new FieldError("object", "field", "message");

  private ControllerExceptionHandler controllerExceptionHandler;

  @MockBean
  MethodArgumentNotValidException methodArgumentNotValidException;

  @MockBean
  CustomException customException;

  @MockBean
  RuntimeException otherException;

  @MockBean
  WebRequest request;

  @BeforeEach
  public void setUp() {
    controllerExceptionHandler = new ControllerExceptionHandler();
  }

  @Test
  public void given_valid_entry_When_globalExceptionHandler_then_return_bad_request() {

    Mockito.when(methodArgumentNotValidException.getFieldError()).thenReturn(FIELD_ERROR);

    ResponseEntity<ErrorMessage> result =
        controllerExceptionHandler.globalExceptionHandler(otherException, request);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());

  }

  @Test
  public void given_valid_entry_When_methodArgumentNotValidExceptionHandler_then_return_bad_request() {

    Mockito.when(methodArgumentNotValidException.getFieldError()).thenReturn(FIELD_ERROR);

    ResponseEntity<ErrorMessage> result =
        controllerExceptionHandler.methodArgumentNotValidExceptionHandler(methodArgumentNotValidException, request);

    assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

  }

  @Test
  public void given_valid_entry_When_customExceptionHandler_then_return_bad_request() {

    ResponseEntity<ErrorMessage> result =
        controllerExceptionHandler.customExceptionHandler(customException, request);

    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

  }

}
