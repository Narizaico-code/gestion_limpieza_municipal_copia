package org.rsosa.gestion_reportes.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.rsosa.gestion_reportes.dominio.dto.TipoReporteDto;
import org.rsosa.gestion_reportes.dominio.dto.VecinoDto;
import org.rsosa.gestion_reportes.persistence.entity.TipoReporte;
import org.rsosa.gestion_reportes.persistence.entity.Vecino;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TipoReporteMapper {
    @Mapping(source = "codigoTipoReporte" , target ="reportTypeId")
    @Mapping(source = "nombre" , target = "nameReportType")
    TipoReporteDto toDto (TipoReporte entity);
    List<TipoReporteDto> toDto (Iterable<TipoReporte> entities);

    @Mapping(source = "reportTypeId" , target = "codigoTipoReporte")
    @Mapping(source = "nameReportType" , target = "nombre")
    TipoReporte toEntity(TipoReporteDto tipoReporteDto);
    void updateEntityFromDto(TipoReporteDto tipoReporteDto, @MappingTarget TipoReporte tipoReporte);
}






