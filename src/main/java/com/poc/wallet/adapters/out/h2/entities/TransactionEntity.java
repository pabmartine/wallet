package com.poc.wallet.adapters.out.h2.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "TRANSACTION")
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  
  @Column(name = "AMMOUNT", nullable = false)
  private Double ammount;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_ACCOUNT", nullable = true, updatable = false)
  private AccountEntity account;

}
