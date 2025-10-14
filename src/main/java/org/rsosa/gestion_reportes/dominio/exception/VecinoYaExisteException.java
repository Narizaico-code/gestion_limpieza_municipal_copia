package org.rsosa.gestion_reportes.dominio.exception;

public class VecinoYaExisteException extends RuntimeException{
    public VecinoYaExisteException(String email){
        super("El email " + email + " ya esta registrado");
    }
}
