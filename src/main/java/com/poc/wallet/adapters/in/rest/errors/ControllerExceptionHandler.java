package com.poc.wallet.adapters.in.rest.errors;

import java.util.Date;

import com.poc.wallet.domain.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Controller Error Handler
 * @author pabmartine
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

  /**
   * Manage request dto errors
   * @param ex the exception
   * @param request the request
   * @return
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex,
      WebRequest request) {

    StringBuilder msg = new StringBuilder(ex.getFieldError().getField()).append(" ").append(ex.getFieldError().getDefaultMessage());

    ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), msg.toString(),
        request.getDescription(false));

    return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
  }

  /**
   * Manage custom errors
   * @param ex the exception
   * @param request the request
   * @return
   */
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorMessage> customExceptionHandler(CustomException ex,
      WebRequest request) {

    StringBuilder msg = new StringBuilder("ERROR: ").append(ex.getMessage());

    ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), msg.toString(),
        request.getDescription(false));

    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
  }

  /**
   * Manage global errors
   * @param ex the exception
   * @param request the request
   * @return
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
