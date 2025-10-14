package org.rsosa.gestion_reportes.web.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.rsosa.gestion_reportes.dominio.dto.MunicipalidadDto;
import org.rsosa.gestion_reportes.persistence.entity.Municipalidad;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MunicipalidadMapper {

    @Mapping(source = "codigoMunicipalidad", target = "municipality_id")
    @Mapping(source = "zona", target = "zone")
    @Mapping(source = "ubicacion", target = "location")
    MunicipalidadDto toDto(Municipalidad entity);
    List<MunicipalidadDto> toDto(List<Municipalidad> entities);

    @InheritInverseConfiguration
    Municipalidad toEntity(MunicipalidadDto dto);
    void updateEntityFromDto(MunicipalidadDto dto, @MappingTarget Municipalidad entity);

}
