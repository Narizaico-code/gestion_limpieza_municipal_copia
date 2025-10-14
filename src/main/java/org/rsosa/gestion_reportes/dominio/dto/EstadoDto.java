package org.rsosa.gestion_reportes.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.rsosa.gestion_reportes.dominio.EstadoEnum;

public record EstadoDto(
        Long state_id,
        @NotNull(message = "El estado no puede estar vacio")
        EstadoEnum name
) {
}