package org.rsosa.gestion_reportes.dominio.dto;

import org.rsosa.gestion_reportes.dominio.PersonalEnum;

public record PersonalDto (
    Long personal_id,
    String name,
    String lastname,
    String number,
    String email,
    PersonalEnum state
){
}
