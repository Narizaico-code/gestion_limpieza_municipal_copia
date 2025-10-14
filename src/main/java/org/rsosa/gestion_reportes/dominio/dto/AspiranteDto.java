package org.rsosa.gestion_reportes.dominio.dto;

public record AspiranteDto(
        Long aspirante_id,
        String name,
        String surname,
        String number,
        String email,
        ContratacionDto hiring
) {
}
