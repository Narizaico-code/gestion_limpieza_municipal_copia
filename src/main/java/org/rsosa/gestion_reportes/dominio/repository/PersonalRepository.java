package org.rsosa.gestion_reportes.dominio.repository;


import org.rsosa.gestion_reportes.dominio.dto.PersonalDto;
import java.util.List;

public interface PersonalRepository {
    List<PersonalDto> obtenerTodo();
    PersonalDto obtenerPersonalPorId(Long id);
    PersonalDto guardarPersonal(PersonalDto personalDto);
    PersonalDto actualizarPersonal(Long id, PersonalDto personalDto);
    void eliminarPersonal(Long id);
}
