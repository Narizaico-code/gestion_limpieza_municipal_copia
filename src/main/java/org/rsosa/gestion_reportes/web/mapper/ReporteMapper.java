package org.rsosa.gestion_reportes.web.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.rsosa.gestion_reportes.dominio.dto.ReporteDto;
import org.rsosa.gestion_reportes.persistence.entity.Reporte;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        EstadoMapper.class,
        VecinoMapper.class,
        PersonalMapper.class,
        TipoReporteMapper.class,
        AdministradorMapper.class
})
public interface ReporteMapper {
    @Mapping(source = "codigoReporte", target = "reportId")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "zona", target = "zone")
    @Mapping(source = "estado", target = "state")
    @Mapping(source = "vecino", target = "neighbor")
    @Mapping(source = "personalAsignado", target = "staff")
    @Mapping(source = "tipoReporte", target = "reportType")
    ReporteDto toDto(Reporte entity);

    List<ReporteDto> toDto(Iterable<Reporte> entities);

    @InheritInverseConfiguration
    @Mapping(source = "zone", target = "zona")
    Reporte toEntity(ReporteDto reporteDto);

    void updateEntityFromDto(ReporteDto reporteDto, @MappingTarget Reporte reporte);
}