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
  
  private double ammount;
  
  public void deposit(String iban, double ammount) {
    this.target = iban;
    this.source = iban;
    this.ammount = ammount;
    this.type = TypeEnum.DEPOSIT;
  }
  
  public void transfer(String source, String target, double ammount) {
    this.source = source;
    this.target = target;
    this.ammount = ammount;
    this.type = TypeEnum.TRANSFER;
  }

}
