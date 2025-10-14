package org.rsosa.gestion_reportes.dominio.exception;

public class VecinoNoExisteException extends RuntimeException{
    public VecinoNoExisteException(Long id){
        super("Vecino con id " + id + " no existe");
    }
    public VecinoNoExisteException(String message){
        super(message);
    }
}