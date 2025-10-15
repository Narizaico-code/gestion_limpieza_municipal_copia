package org.rsosa.gestion_reportes.dominio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CalificacionDto(
        Long calificacionId,

        @NotNull(message = "La calificación es obligatoria.")
        @Min(value = 1, message = "La calificación mínima es 1.")
        @Max(value = 5, message = "La calificación máxima es 5.")
        Integer value,

        @NotNull(message = "Debe asociar un reporte para calificar.")
        ReporteDto report,

        @NotNull(message = "Debe asociar un vecino a la calificación.")
        VecinoDto neighbor
) {
    public Long getCalificacionId() {
        return calificacionId;
    }

    public Integer getValue() {
        return value;
    }

    public ReporteDto getReport() {
        return report;
    }

    public VecinoDto getNeighbor() {
        return neighbor;
    }
}