package org.rsosa.gestion_reportes.dominio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContratacionDto (
        Long hiring_id,
        @NotNull(message = "El salario no puede estar vacío.")
        Double salary,
        @NotBlank(message = "El nombre de la vacante no puede estar vacío.")
        String vacancy,
        @NotNull(message = "Debe especificar el número de vacantes disponibles.")
        @Min(value = 1, message = "Debe haber al menos 1 vacante disponible.")
        Integer available_vaca,
        @NotNull(message = "El código de la municipalidad es obligatorio para la contratación.")
        MunicipalidadDto municipality
){
}