package org.rsosa.gestion_reportes.dominio.exception;

public class ReportePersonalEnUsoException extends RuntimeException {
    public ReportePersonalEnUsoException(Long id) {
        super("El personal con id " + id + " esta ocupado en el momento");
    }
}