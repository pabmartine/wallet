package com.poc.wallet.adapters.out.h2.mappers;

import java.util.List;

import com.poc.wallet.adapters.out.h2.entities.AccountEntity;
import com.poc.wallet.domain.Account;
import org.mapstruct.Mapper;

/**
 * Mapping interface from entity to domain
 * @author pabmartine
 *
 */
@Mapper(componentModel = "spring")
public interface AccountEntityMapper {

  /**
   * Transforms a entity object into a domain
   * @param entity the account
   * @return the domain
   */
  Account entityToDomain(AccountEntity entity);

  /**
   * Transforms a domain object into an entity
   * @param domain the account
   * @return the entity
   */
  AccountEntity domainToEntity(Account domain);

  /**
   * Transforms a list of entities objects into a list of domains
   * @param entities the account
   * @return the domain
   */
  List<Account> entitiesToDomains(List<AccountEntity> entities);
}
