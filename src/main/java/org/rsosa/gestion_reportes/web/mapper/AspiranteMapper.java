package org.rsosa.gestion_reportes.web.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.rsosa.gestion_reportes.dominio.dto.AspiranteDto;
import org.rsosa.gestion_reportes.persistence.entity.Aspirante;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AspiranteMapper {
    @Mapping (source = "codigoAspirante", target = "aspirante_id" )
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "apellido", target = "surname")
    @Mapping(source = "telefono", target = "number")
    @Mapping(source = "correo", target = "email")
    @Mapping(source = "contratacion", target = "hiring")
    AspiranteDto toDto (Aspirante entity);
    List<AspiranteDto> toDto (List<Aspirante> entity);

    @InheritInverseConfiguration
    Aspirante toEntity (AspiranteDto dto);
    void updateEntityFromDto(AspiranteDto dto, @MappingTarget Aspirante entity);


}
