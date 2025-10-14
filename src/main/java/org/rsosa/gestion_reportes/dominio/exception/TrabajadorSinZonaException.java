package org.rsosa.gestion_reportes.dominio.exception;

public class TrabajadorSinZonaException extends RuntimeException {
    public TrabajadorSinZonaException(Long id) {
        super("El personal con código " + id + " no tiene una zona de municipalidad asignada.");
    }
}
