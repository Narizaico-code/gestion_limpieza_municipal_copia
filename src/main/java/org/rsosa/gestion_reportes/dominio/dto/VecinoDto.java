package org.rsosa.gestion_reportes.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record VecinoDto(
        Long neighbor_id,
        @NotBlank(message = "El nombre no puede ser nulo")
        String name_neighbor,
        String phone_number,
        @NotBlank(message = "El correo electronico no puede estar vacío")
        String email
) {
}