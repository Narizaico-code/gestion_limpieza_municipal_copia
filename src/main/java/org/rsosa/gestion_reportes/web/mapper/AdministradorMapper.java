// src/main/java/org/rsosa/gestion_reportes/web/mapper/AdministradorMapper.java
package org.rsosa.gestion_reportes.web.mapper;

import org.mapstruct.*;
import org.rsosa.gestion_reportes.dominio.dto.AdministradorDto;
import org.rsosa.gestion_reportes.persistence.entity.Administrador;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AdministradorMapper {
    @Mapping(source = "codigoAdmin", target = "adminId")
    @Mapping(source = "usuario", target = "username")
    @Mapping(source = "contraseña", target = "password")
    @Mapping(source = "nombreCompleto", target = "fullName")
    AdministradorDto toDto(Administrador entity);
    List<AdministradorDto> toDto(Iterable<Administrador> entities);

    @Mapping(source = "adminId", target = "codigoAdmin")
    @Mapping(source = "username", target = "usuario")
    @Mapping(source = "password", target = "contraseña")
    @Mapping(source = "fullName", target = "nombreCompleto")
    Administrador toEntity(AdministradorDto dto);

    void updateEntityFromDto(AdministradorDto dto, @MappingTarget Administrador entity);
}
