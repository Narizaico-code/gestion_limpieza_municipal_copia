package org.rsosa.gestion_reportes.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.rsosa.gestion_reportes.dominio.dto.TrabajadorMunicipalDto;
import org.rsosa.gestion_reportes.persistence.entity.TrabajadorMunicipalidad;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonalMapper.class, MunicipalidadMapper.class})
public interface TrabajadorMunicipalidadMapper {
    @Mapping(source = "codigoTrabajadorMunicipal", target = "municipal_worker_id")
    @Mapping(source = "personal", target = "staff")
    @Mapping(source = "municipalidad", target = "municipality")
    TrabajadorMunicipalDto toDto(TrabajadorMunicipalidad entity);

    List<TrabajadorMunicipalDto> toDto(Iterable<TrabajadorMunicipalidad> entities);

    @Mapping(source = "municipal_worker_id", target = "codigoTrabajadorMunicipal")
    TrabajadorMunicipalidad toEntity(TrabajadorMunicipalDto dto);

    void updateEntityFromDto(TrabajadorMunicipalDto dto, @MappingTarget TrabajadorMunicipalidad entity);
}