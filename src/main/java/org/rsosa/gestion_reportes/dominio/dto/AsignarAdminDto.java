package org.rsosa.gestion_reportes.dominio.dto;

import jakarta.validation.constraints.NotNull;

public record AsignarAdminDto(
        @NotNull(message = "El ID del administrador es obligatorio")
        Long adminId,
        @NotNull(message = "El ID de la municipalidad es obligatorio")
        Long municipalidadId
) {
    public Long getAdminId() {
        return adminId;
    }

    public Long getMunicipalidadId() {
        return municipalidadId;
    }
}