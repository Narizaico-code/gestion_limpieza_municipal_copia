package org.rsosa.gestion_reportes.dominio.exception;

public class PersonalNoExisteException extends RuntimeException {
    public PersonalNoExisteException(Long id) {
        super("El personal con codigo " + id + " no existe");
    }
}
