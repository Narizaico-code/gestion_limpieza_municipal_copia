package org.rsosa.gestion_reportes.web.mapper;

import org.mapstruct.Named;
import org.rsosa.gestion_reportes.dominio.PersonalEnum;

public class EstadoPersonalMapper {
    @Named("generarState")
    public static PersonalEnum generarState(String estado){
        if (estado == null) return null;
        return switch (estado.trim().toUpperCase()){
            case "ASIGNADO" -> PersonalEnum.ASIGNADO;
            case "LIBRE" -> PersonalEnum.LIBRE;
            default -> null;
        };
    }

    @Named("generarEstado")
    public static String generarEstado(PersonalEnum personalEnum){
        if (personalEnum == null)return null;
        return switch (personalEnum){
            case LIBRE -> "LIBRE";
            case ASIGNADO -> "ASIGNADO";
            default -> null;
        };
    }
}
