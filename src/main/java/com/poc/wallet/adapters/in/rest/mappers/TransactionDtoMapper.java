package com.poc.wallet.adapters.in.rest.mappers;

import java.util.List;

import com.poc.wallet.adapters.in.rest.dto.TransactionDto;
import com.poc.wallet.domain.Transaction;
import org.mapstruct.Mapper;

/**
 * Mapping interface from domain to dto
 * @author pabmartine
 *
 */
@Mapper(componentModel = "spring")
public interface TransactionDtoMapper {

  /**
   * Transforms a domain object into a dto
   * @param domain the transaction
   * @return the dto
   */
  TransactionDto domainToDto(Transaction domain);

  /**
   * Transforms a dto object into an domain
   * @param dto the transaction
   * @return the domain
   */
  Transaction dtoToDomain(TransactionDto dto);

  /**
   * Transforms a list of domains objects into a list of dtos
   * @param entities the transaction
   * @return the dto
   */
  List<TransactionDto> domainsToDtos(List<Transaction> domains);
}
