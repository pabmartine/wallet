package com.poc.wallet.adapters.in.rest.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer dto class
 * @author pabmartine
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

  @NotNull
  private String nif;

  @NotNull
  private String name;

  @NotNull
  private String surname;

}
