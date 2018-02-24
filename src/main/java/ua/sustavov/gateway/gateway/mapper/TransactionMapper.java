package ua.sustavov.gateway.gateway.mapper;

import org.mapstruct.Mapper;
import ua.sustavov.gateway.gateway.dto.TransactionDto;
import ua.sustavov.gateway.gateway.entity.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends BaseMapper<TransactionDto, Transaction>{
}
