package org.rsosa.gestion_reportes.dominio.dto;

public record MunicipalidadDto (

        Long municipalityId,
        String zone,
        String location

){
    public Long getMunicipalityId() {
        return municipalityId;
    }

    public String getZone() {
        return zone;
    }

    public String getLocation() {
        return location;
    }
}
