package org.rsosa.gestion_reportes.dominio.dto;

public record AdministradorMunicipalidadDto(
        Long admin_municipalidad_id,
        AdministradorDto administrador,
        MunicipalidadDto municipalidad
) {
}