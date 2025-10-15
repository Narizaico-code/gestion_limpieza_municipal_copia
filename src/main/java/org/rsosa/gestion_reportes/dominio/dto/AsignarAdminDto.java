package org.rsosa.gestion_reportes.dominio.dto;

import jakarta.validation.constraints.NotNull;

public record AsignarAdminDto(
        @NotNull(message = "El ID del administrador es obligatorio")
        Long admin_id,
        @NotNull(message = "El ID de la municipalidad es obligatorio")
        Long municipalidad_id
) {
}