package org.rsosa.gestion_reportes.web.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.rsosa.gestion_reportes.dominio.dto.CalificacionDto;
import org.rsosa.gestion_reportes.persistence.entity.Calificacion;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReporteMapper.class, VecinoMapper.class})
public interface CalificacionMapper {

    @Mapping(source = "codigoCalificacion", target = "calificacion_id")
    @Mapping(source = "valoracion", target = "value")
    @Mapping(source = "reporte", target = "report")
    @Mapping(source = "vecino", target = "neighbor")
    CalificacionDto toDto(Calificacion entity);
    List<CalificacionDto> toDto(Iterable<Calificacion> entities);

    @InheritInverseConfiguration
    Calificacion toEntity(CalificacionDto dto);
    void updateEntityFromDto(CalificacionDto calificacionDto, @MappingTarget Calificacion calificacion);
}