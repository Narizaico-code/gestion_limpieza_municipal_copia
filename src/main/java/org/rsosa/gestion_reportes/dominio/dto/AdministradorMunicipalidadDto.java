package org.rsosa.gestion_reportes.dominio.dto;

public record AdministradorMunicipalidadDto(
        Long adminMunicipalidadId,
        AdministradorDto administrador,
        MunicipalidadDto municipalidad
) {
    public Long getAdminMunicipalidadId() {
        return adminMunicipalidadId;
    }

    public AdministradorDto getAdministrador() {
        return administrador;
    }

    public MunicipalidadDto getMunicipalidad() {
        return municipalidad;
    }
}