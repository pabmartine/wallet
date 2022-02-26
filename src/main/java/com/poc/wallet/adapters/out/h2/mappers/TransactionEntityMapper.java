package com.poc.wallet.adapters.out.h2.mappers;

import java.util.List;

import com.poc.wallet.adapters.out.h2.entities.TransactionEntity;
import com.poc.wallet.domain.Transaction;
import org.mapstruct.Mapper;

/**
 * Mapping interface from entity to domain
 * @author pabmartine
 *
 */
@Mapper(componentModel = "spring")
public interface TransactionEntityMapper {

  /**
   * Transforms a entity object into a domain
   * @param entity the transaction
   * @return the domain
   */
  Transaction entityToDomain(TransactionEntity entity);

  /**
   * Transforms a domain object into an entity
   * @param domain the transaction
   * @return the entity
   */
  TransactionEntity domainToEntity(Transaction domain);

  /**
   * Transforms a list of entities objects into a list of domains
   * @param entities the transaction
   * @return the domain
   */
  List<Transaction> entitiesToDomains(List<TransactionEntity> entities);
}
