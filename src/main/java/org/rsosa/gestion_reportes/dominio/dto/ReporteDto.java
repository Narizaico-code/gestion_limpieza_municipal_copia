package org.rsosa.gestion_reportes.dominio.dto;

public record ReporteDto(
        Long report_id,
        String description,
        String zone,
        EstadoDto state,
        VecinoDto neighbor,
        PersonalDto staff,
        TipoReporteDto report_type
) {
}