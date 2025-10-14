package org.rsosa.gestion_reportes.web.mapper;

import org.mapstruct.Named;
import org.rsosa.gestion_reportes.dominio.EstadoEnum;

public class NombreEstadoMapper {
    @Named("generarName")
    public static EstadoEnum generarName(String nombre){
        if (nombre == null) return null;
        return switch (nombre.trim().toUpperCase()){
            case "ASIGNADO" -> EstadoEnum.ASIGNADO;
            case "PENDIENTE" -> EstadoEnum.PENDIENTE;
            case "RESUELTO" -> EstadoEnum.RESUELTO;
            default -> null;
        };
    }
    @Named("generarNombre")
    public static String generarNombre(EstadoEnum estadoEnum){
        if (estadoEnum == null) return null;
        return switch (estadoEnum){
            case RESUELTO -> "RESUELTO";
            case ASIGNADO -> "ASIGNADO";
            case PENDIENTE -> "PENDIENTE";
            default -> null;
        };
    }
}