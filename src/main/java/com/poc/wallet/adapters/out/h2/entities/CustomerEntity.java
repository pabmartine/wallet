package com.poc.wallet.adapters.out.h2.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Customer entity class
 * @author pabmartine
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  private Long id;

  @Column(name = "NIF", nullable = false)
  private String nif;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "SURNAME", nullable = false)
  private String surname;

  @Column(name = "CREATION_DATE", nullable = false)
  @CreationTimestamp
  private Date creationDate;

  @OneToMany(mappedBy = "customer")
  private List<AccountEntity> accounts;

}
