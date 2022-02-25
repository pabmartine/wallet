package com.poc.wallet.adapters.out.h2.repositories;

import java.util.List;

import com.poc.wallet.adapters.out.h2.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for transaction entity
 * 
 * @author pabmartine
 *
 */
public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Integer> {

  @Query("SELECT t FROM TransactionEntity t where t.account.iban = :iban")
  List<TransactionEntity> findAllByIban(@Param("iban") String iban);

}
