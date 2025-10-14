package org.rsosa.gestion_reportes.dominio.exception;

public class EstadoNoExisteException extends RuntimeException{
    public EstadoNoExisteException(Long id){
        super("El estado con codigo " + id + " no existe");
    }
    public EstadoNoExisteException(String nombre){
        super("El estado con nombre " + nombre + " no existe");
    }
}