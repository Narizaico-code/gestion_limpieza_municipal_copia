package org.rsosa.gestion_reportes.dominio.dto;

public record TipoReporteDto(
        Long reportTypeId,
        String nameReportType
) {
    public Long getReportTypeId() {
        return reportTypeId;
    }

    public String getNameReportType() {
        return nameReportType;
    }
}
