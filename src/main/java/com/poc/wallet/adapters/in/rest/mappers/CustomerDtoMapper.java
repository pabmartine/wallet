package com.poc.wallet.adapters.in.rest.mappers;

import java.util.List;

import com.poc.wallet.adapters.in.rest.dto.CustomerDto;
import com.poc.wallet.adapters.in.rest.dto.CustomerDto;
import com.poc.wallet.domain.Customer;
import org.mapstruct.Mapper;

/**
 * Mapping interface from domain to dto
 * @author pabmartine
 *
 */
@Mapper(componentModel = "spring")
public interface CustomerDtoMapper {

  /**
   * Transforms a domain object into a dto
   * @param domain the customer
   * @return the dto
   */
  CustomerDto domainToDto(Customer domain);

  /**
   * Transforms a dto object into an domain
   * @param dto the customer
   * @return the domain
   */
  Customer dtoToDomain(CustomerDto dto);

  /**
   * Transforms a list of domains objects into a list of dtos
   * @param entities the customer
   * @return the dto
   */
  List<CustomerDto> domainsToDtos(List<Customer> domains);
}
