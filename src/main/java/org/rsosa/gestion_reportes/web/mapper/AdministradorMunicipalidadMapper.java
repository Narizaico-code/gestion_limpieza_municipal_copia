package org.rsosa.gestion_reportes.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.rsosa.gestion_reportes.dominio.dto.AdministradorMunicipalidadDto;
import org.rsosa.gestion_reportes.persistence.entity.AdministradorMunicipalidad;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AdministradorMapper.class, MunicipalidadMapper.class})
public interface AdministradorMunicipalidadMapper {
    @Mapping(source = "codigoAdminMunicipalidad", target = "admin_municipalidad_id")
    @Mapping(source = "administrador", target = "administrador")
    @Mapping(source = "municipalidad", target = "municipalidad")
    AdministradorMunicipalidadDto toDto(AdministradorMunicipalidad entity);

    List<AdministradorMunicipalidadDto> toDto(Iterable<AdministradorMunicipalidad> entities);

    @Mapping(source = "admin_municipalidad_id", target = "codigoAdminMunicipalidad")
    AdministradorMunicipalidad toEntity(AdministradorMunicipalidadDto dto);

    void updateEntityFromDto(AdministradorMunicipalidadDto dto, @MappingTarget AdministradorMunicipalidad entity);
}