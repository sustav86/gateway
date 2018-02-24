package ua.sustavov.gateway.gateway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ua.sustavov.gateway.gateway.entity.AuthTransaction;
import ua.sustavov.gateway.gateway.entity.Transaction;

@Mapper(componentModel = "spring")
public interface AuthTransToTransMapper {

    @Mappings({
            @Mapping(target = "customerId", source = "customerAccountCode"),
            @Mapping(target = "billToFirstName", source = "firstName"),
            @Mapping(target = "billToLastName", source = "lastName"),
            @Mapping(target = "billToAddress", source = "street"),
            @Mapping(target = "billToCity", source = "city"),
            @Mapping(target = "billToState", source = "state"),
            @Mapping(target = "billToZip", source = "zipCode"),
            @Mapping(target = "billToPhoneNumber", source = "phoneNumber"),
    })
    AuthTransaction transToAuthTrans(Transaction transaction);

    Transaction authTransToTrans(AuthTransaction transaction);

}
