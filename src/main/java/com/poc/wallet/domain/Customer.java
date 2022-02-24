package com.poc.wallet.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer domain class
 * @author pabmartine
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

  private String nif;

  private String name;

  private String surname;

  private Date creationDate;

  private List<Account> transactions;

}
