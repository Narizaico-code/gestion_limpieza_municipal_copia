package org.rsosa.gestion_reportes.dominio.exception;

public class ReporteNoExisteException extends RuntimeException {
    public ReporteNoExisteException(Long id) {
        super("El reporte con id " + id + " no existe");
    }
}
