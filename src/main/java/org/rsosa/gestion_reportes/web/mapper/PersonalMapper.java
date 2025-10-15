// src/main/java/org/rsosa/gestion_reportes/web/mapper/PersonalMapper.java
package org.rsosa.gestion_reportes.web.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.rsosa.gestion_reportes.dominio.dto.PersonalDto;
import org.rsosa.gestion_reportes.persistence.entity.Personal;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EstadoPersonalMapper.class})
public interface PersonalMapper {
    @Mapping(source = "codigoPersonal", target = "personal_id")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "apellido", target = "lastname")
    @Mapping(source = "telefono", target = "number")
    @Mapping(source = "correo", target = "email")
    @Mapping(source = "estado", target = "state", qualifiedByName = "generarState")
    PersonalDto toDto(Personal entity);

    List<PersonalDto> toDto(List<Personal> entities);

    @Mapping(source = "personal_id", target = "codigoPersonal")
    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "lastname", target = "apellido")
    @Mapping(source = "number", target = "telefono")
    @Mapping(source = "email", target = "correo")
    @Mapping(source = "state", target = "estado", qualifiedByName = "generarEstado")
    Personal toEntity(PersonalDto personalDto);
    void updateEntityFromDto(PersonalDto dto, @MappingTarget Personal entity);
}