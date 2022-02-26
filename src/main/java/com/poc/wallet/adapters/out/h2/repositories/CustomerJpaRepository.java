package com.poc.wallet.adapters.out.h2.repositories;

import com.poc.wallet.adapters.out.h2.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for customer entity
 * 
 * @author pabmartine
 *
 */
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {

  CustomerEntity findByNif(String nif);

}
