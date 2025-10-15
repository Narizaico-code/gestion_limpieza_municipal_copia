package org.rsosa.gestion_reportes.dominio.dto;

public record AspiranteDto(
        Long aspiranteId,
        String name,
        String surname,
        String number,
        String email,
        ContratacionDto hiring
) {
    public Long getAspiranteId() {
        return aspiranteId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public ContratacionDto getHiring() {
        return hiring;
    }
}
