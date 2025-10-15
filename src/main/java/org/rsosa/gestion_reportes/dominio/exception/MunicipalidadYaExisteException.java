package org.rsosa.gestion_reportes.dominio.exception;

public class MunicipalidadYaExisteException extends RuntimeException {
    public MunicipalidadYaExisteException(String zona) {
        super("La zona " + zona + " ya existe");
    }
}
