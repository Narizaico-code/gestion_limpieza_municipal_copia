package org.rsosa.gestion_reportes.dominio.service;

import org.rsosa.gestion_reportes.dominio.dto.PersonalDto;
import org.rsosa.gestion_reportes.dominio.repository.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    public List<PersonalDto> obtenerTodo() {
        return this.personalRepository.obtenerTodo();
    }

    public PersonalDto obtenerPersonalPorId(Long id) {
        return this.personalRepository.obtenerPersonalPorId(id);
    }

    public PersonalDto guardarPersonal(PersonalDto personalDto) {
        return this.personalRepository.guardarPersonal(personalDto);
    }

    public PersonalDto actualizarPersonal(Long id, PersonalDto personalDto) {
        return this.personalRepository.actualizarPersonal(id, personalDto);
    }

    public void eliminarPersonal(Long id) {
        this.personalRepository.eliminarPersonal(id);
    }
}
