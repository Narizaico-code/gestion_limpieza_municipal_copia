package org.rsosa.gestion_reportes.dominio.dto;

public record TrabajadorMunicipalDto(
        Long municipalWorkerId,
        PersonalDto staff,
        MunicipalidadDto municipality
) {
    public Long getMunicipalWorkerId() {
        return municipalWorkerId;
    }

    public PersonalDto getStaff() {
        return staff;
    }

    public MunicipalidadDto getMunicipality() {
        return municipality;
    }
}
