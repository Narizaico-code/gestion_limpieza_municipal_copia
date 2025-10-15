package org.rsosa.gestion_reportes.dominio.dto;

public record ModReporteDto(
        EstadoDto state,
        PersonalDto staff
) {
    public EstadoDto getState() {
        return state;
    }

    public PersonalDto getStaff() {
        return staff;
    }
}