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
    @Mapping(source = "codigoVecino", target = "neighbor_id")
    @Mapping(source = "nombre", target = "name_neighbor")
    @Mapping(source = "telefono", target = "phone_number")
    @Mapping(source = "correoElectronico", target = "email")
    VecinoDto toDto(Vecino entity);
    List<VecinoDto> toDto(Iterable<Vecino> entities);

    @Mapping(source = "neighbor_id", target = "codigoVecino")
    @Mapping(source = "name_neighbor" , target = "nombre")
    @Mapping(source = "phone_number", target = "telefono")
    @Mapping(source = "email", target = "correoElectronico")
    Vecino toEntity(VecinoDto vecinoDto);
    void updateEntityFromDto(VecinoDto vecinoDto, @MappingTarget Vecino vecino);
}