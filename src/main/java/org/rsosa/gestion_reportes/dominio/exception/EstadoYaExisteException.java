package org.rsosa.gestion_reportes.dominio.exception;

public class EstadoYaExisteException extends RuntimeException{
    public EstadoYaExisteException(String nombre){
        super("El estado con nombre " + nombre + " ya existe");
    }
}
