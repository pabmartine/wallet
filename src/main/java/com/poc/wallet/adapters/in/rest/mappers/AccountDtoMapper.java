package com.poc.wallet.adapters.in.rest.mappers;

import java.util.List;

import com.poc.wallet.adapters.in.rest.dto.AccountDto;
import com.poc.wallet.domain.Account;
import org.mapstruct.Mapper;

/**
 * Mapping interface from domain to dto
 * @author pabmartine
 *
 */
@Mapper(componentModel = "spring")
public interface AccountDtoMapper {

  /**
   * Transforms a domain object into a dto
   * @param domain the account
   * @return the dto
   */
  AccountDto domainToDto(Account domain);

  /**
   * Transforms a dto object into an domain
   * @param dto the account
   * @return the domain
   */
  Account dtoToDomain(AccountDto dto);

  /**
   * Transforms a list of domains objects into a list of dtos
   * @param entities the account
   * @return the dto
   */
  List<AccountDto> domainsToDtos(List<Account> domains);
}
