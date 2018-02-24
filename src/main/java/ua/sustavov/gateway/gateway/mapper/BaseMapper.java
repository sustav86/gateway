package ua.sustavov.gateway.gateway.mapper;

import org.mapstruct.InheritInverseConfiguration;

public interface BaseMapper<DTO, ENTITY> {

    DTO toDto(ENTITY entity);

    @InheritInverseConfiguration
    ENTITY toEntity(DTO dto);
}
