package org.rsosa.gestion_reportes.dominio.dto;

public record ModReporteDto(
        EstadoDto state,
        PersonalDto staff
) {
}