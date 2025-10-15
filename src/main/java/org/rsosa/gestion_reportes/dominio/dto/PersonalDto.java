package org.rsosa.gestion_reportes.dominio.dto;

import org.rsosa.gestion_reportes.dominio.PersonalEnum;

public record PersonalDto (
    Long personalId,
    String name,
    String lastname,
    String number,
    String email,
    PersonalEnum state
){
    public Long getPersonalId() {
        return personalId;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public PersonalEnum getState() {
        return state;
    }
}
