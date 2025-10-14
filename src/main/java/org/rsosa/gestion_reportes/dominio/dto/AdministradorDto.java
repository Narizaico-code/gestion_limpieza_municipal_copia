package org.rsosa.gestion_reportes.dominio.dto;

public record AdministradorDto(
        Long admin_id,
        String username,
        String password,
        String fullName
) {
}
