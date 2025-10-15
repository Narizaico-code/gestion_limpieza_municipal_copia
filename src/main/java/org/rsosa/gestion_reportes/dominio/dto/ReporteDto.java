package org.rsosa.gestion_reportes.dominio.dto;

import lombok.Data;

public record ReporteDto(
        Long reportId,
        String description,
        String zone,
        EstadoDto state,
        VecinoDto neighbor,
        PersonalDto staff,
        TipoReporteDto reportType
) {
    public Long getReportId() {
        return reportId;
    }

    public String getDescription() {
        return description;
    }

    public String getZone() {
        return zone;
    }

    public EstadoDto getState() {
        return state;
    }

    public VecinoDto getNeighbor() {
        return neighbor;
    }

    public PersonalDto getStaff() {
        return staff;
    }

    public TipoReporteDto getReportType() {
        return reportType;
    }
}