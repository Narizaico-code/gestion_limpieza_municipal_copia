package org.rsosa.gestion_reportes.web.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.rsosa.gestion_reportes.dominio.dto.VecinoDto;
import org.rsosa.gestion_reportes.persistence.entity.Vecino;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VecinoMapper {
    @Mapping(source = "codigoVecino", target = "neighborId")
    @Mapping(source = "nombre", target = "nameNeighbor")
    @Mapping(source = "contrasena", target = "password")
    @Mapping(source = "telefono", target = "phoneNumber")
    @Mapping(source = "correoElectronico", target = "email")
    VecinoDto toDto(Vecino entity);
    List<VecinoDto> toDto(Iterable<Vecino> entities);

    @Mapping(source = "neighborId", target = "codigoVecino")
    @Mapping(source = "nameNeighbor" , target = "nombre")
    @Mapping(source = "password", target = "contrasena")
    @Mapping(source = "phoneNumber", target = "telefono")
    @Mapping(source = "email", target = "correoElectronico")
    Vecino toEntity(VecinoDto vecinoDto);
    void updateEntityFromDto(VecinoDto vecinoDto, @MappingTarget Vecino vecino);
}