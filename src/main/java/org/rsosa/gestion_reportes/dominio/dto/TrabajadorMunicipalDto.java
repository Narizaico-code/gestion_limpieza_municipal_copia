package org.rsosa.gestion_reportes.dominio.dto;

public record TrabajadorMunicipalDto(
        Long municipal_worker_id,
        PersonalDto staff,
        MunicipalidadDto municipality
) {
}
