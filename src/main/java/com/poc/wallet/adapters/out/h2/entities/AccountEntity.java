package com.poc.wallet.adapters.out.h2.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account entity class
 * @author pabmartine
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {

  @Id
  @Column(name = "ID", nullable = false)
  private Integer id;

  @Column(name = "IBAN", nullable = false)
  private String iban;

  @Column(name = "BALANCE", nullable = false)
  private double balance;

  @OneToMany(mappedBy = "account")
  private List<TransactionEntity> transactions;

  @ManyToOne
  @JoinColumn(name = "ID_CUSTOMER", nullable = false, updatable = false)
  private CustomerEntity customer;

}
