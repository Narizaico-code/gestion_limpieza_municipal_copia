package org.rsosa.gestion_reportes.dominio.dto;

public record AdministradorDto(
        Long adminId,
        String username,
        String password,
        String fullName
) {
    public Long getAdminId() {
        return adminId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
}
