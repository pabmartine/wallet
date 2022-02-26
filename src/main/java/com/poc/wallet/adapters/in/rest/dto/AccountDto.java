package com.poc.wallet.adapters.in.rest.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account dto class
 * @author pabmartine
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

  @NotNull
  private String iban;

  @NotNull
  private Double balance;

  private List<TransactionDto> transactions;

}
