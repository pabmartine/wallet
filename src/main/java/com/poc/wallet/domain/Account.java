package com.poc.wallet.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account domain class
 * @author pabmartine
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  private String iban;

  private double balance;

  private List<Transaction> transactions;

  public Account(String iban) {
    this.iban = iban;
  }
  
}
