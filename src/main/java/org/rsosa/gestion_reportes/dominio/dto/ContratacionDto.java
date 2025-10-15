package org.rsosa.gestion_reportes.dominio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContratacionDto (
        Long hiringId,
        @NotNull(message = "El salario no puede estar vacío.")
        Double salary,
        @NotBlank(message = "El nombre de la vacante no puede estar vacío.")
        String vacancy,
        @NotNull(message = "Debe especificar el número de vacantes disponibles.")
        @Min(value = 1, message = "Debe haber al menos 1 vacante disponible.")
        Integer availableVaca,
        @NotNull(message = "El código de la municipalidad es obligatorio para la contratación.")
        MunicipalidadDto municipality
){
    public Long getHiringId() {
        return hiringId;
    }

    public Double getSalary() {
        return salary;
    }

    public String getVacancy() {
        return vacancy;
    }

    public Integer getAvailableVaca() {
        return availableVaca;
    }

    public MunicipalidadDto getMunicipality() {
        return municipality;
    }
}