package com.poc.wallet.adapters.out.h2.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Transaction entity class
 * @author pabmartine
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class TransactionEntity {

  @Id
  @Column(name = "ID", nullable = false)
  private Long id;

  @Column(name = "SOURCE", nullable = false)
  private String source;

  @Column(name = "TARGET", nullable = false)
  private String target;

  @Column(name = "TYPE", nullable = false)
  private String type;

  @Column(name = "CREATION_DATE", nullable = false)
  @CreationTimestamp
  private Date creationDate;

  @ManyToOne
  @JoinColumn(name = "ID_ACCOUNT", nullable = false, updatable = false)
  private AccountEntity account;

}
