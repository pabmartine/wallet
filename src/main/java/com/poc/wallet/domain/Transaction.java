package com.poc.wallet.domain;

import java.util.Date;

import com.poc.wallet.domain.constants.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Transaction domain class
 * @author pabmartine
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
  
  private String source;
  
  private String target;

  private TypeEnum type;

  private Date creationDate;

}
