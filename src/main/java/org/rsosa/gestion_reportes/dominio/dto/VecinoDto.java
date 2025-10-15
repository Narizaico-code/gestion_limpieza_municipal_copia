package org.rsosa.gestion_reportes.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record VecinoDto(
        Long neighborId,
        @NotBlank(message = "El nombre no puede ser nulo")
        String nameNeighbor,
        String password,
        String phoneNumber,
        @NotBlank(message = "El correo electronico no puede estar vacío")
        String email
) {
    public Long getNeighborId() {
        return neighborId;
    }

    public String getNameNeighbor() {
        return nameNeighbor;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}