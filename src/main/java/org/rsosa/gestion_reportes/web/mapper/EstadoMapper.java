package org.rsosa.gestion_reportes.web.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.rsosa.gestion_reportes.dominio.dto.EstadoDto;
import org.rsosa.gestion_reportes.persistence.entity.Estado;

import java.util.List;

@Mapper(componentModel = "spring", uses = {NombreEstadoMapper.class})
public interface EstadoMapper {
    @Mapping(source = "codigoEstado", target = "state_id")
    @Mapping(source = "nombre", target = "name", qualifiedByName = "generarName")
    EstadoDto toDto(Estado entity);
    List<EstadoDto> toDto(Iterable<Estado> entities);

    @InheritInverseConfiguration
    @Mapping(source = "name", target = "nombre", qualifiedByName = "generarNombre")
    Estado toEntity(EstadoDto estadoDto);
    void updateEntityFromDto(EstadoDto estadoDto, @MappingTarget Estado estado);
}