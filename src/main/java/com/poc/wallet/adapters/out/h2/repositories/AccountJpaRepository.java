package com.poc.wallet.adapters.out.h2.repositories;

import java.util.List;

import com.poc.wallet.adapters.out.h2.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for account entity
 * 
 * @author pabmartine
 *
 */
public interface AccountJpaRepository extends JpaRepository<AccountEntity, Integer> {

  AccountEntity findByIban(String iban);

  List<AccountEntity> findAllByCustomer(String nif);

}
