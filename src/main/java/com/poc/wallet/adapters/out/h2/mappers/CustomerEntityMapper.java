package com.poc.wallet.adapters.out.h2.mappers;

import java.util.List;

import com.poc.wallet.adapters.out.h2.entities.CustomerEntity;
import com.poc.wallet.domain.Customer;
import org.mapstruct.Mapper;

/**
 * Mapping interface from entity to domain
 * @author pabmartine
 *
 */
@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

  /**
   * Transforms a entity object into a domain
   * @param entity the customer
   * @return the domain
   */
  Customer entityToDomain(CustomerEntity entity);

  /**
   * Transforms a domain object into an entity
   * @param domain the customer
   * @return the entity
   */
  CustomerEntity domainToEntity(Customer domain);

  /**
   * Transforms a list of entities objects into a list of domains
   * @param entities the customer
   * @return the domain
   */
  List<Customer> entitiesToDomain(List<CustomerEntity> entities);
}
