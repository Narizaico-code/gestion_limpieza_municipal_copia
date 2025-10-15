package org.rsosa.gestion_reportes.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.rsosa.gestion_reportes.dominio.dto.MunicipalidadDto;
import org.rsosa.gestion_reportes.persistence.entity.Municipalidad;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MunicipalidadMapper {
    @Mapping(source = "codigoMunicipalidad", target = "municipalityId")
    @Mapping(source = "zona", target = "zone")
    @Mapping(source = "ubicacion", target = "location")
    MunicipalidadDto toDto(Municipalidad entity);

    List<MunicipalidadDto> toDto(Iterable<Municipalidad> entities);

    @Mapping(source = "municipalityId", target = "codigoMunicipalidad")
    @Mapping(source = "zone", target = "zona")
    @Mapping(source = "location", target = "ubicacion")
    Municipalidad toEntity(MunicipalidadDto dto);

    void updateEntityFromDto(MunicipalidadDto dto, @MappingTarget Municipalidad entity);
}