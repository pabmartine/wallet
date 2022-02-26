package com.poc.wallet.adapters.in.rest.dto;

import java.util.Date;

import com.poc.wallet.domain.constants.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Transaction dto class
 * @author pabmartine
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

  private String source;

  private String target;

  private TypeEnum type;

  private Date creationDate;

  private double ammount;

}
