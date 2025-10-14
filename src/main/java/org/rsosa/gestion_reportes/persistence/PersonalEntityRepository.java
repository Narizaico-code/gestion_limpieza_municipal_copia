// src/main/java/org/rsosa/gestion_reportes/persistence/PersonalEntityRepository.java
package org.rsosa.gestion_reportes.persistence;

import org.rsosa.gestion_reportes.dominio.dto.PersonalDto;
import org.rsosa.gestion_reportes.dominio.repository.PersonalRepository;
import org.rsosa.gestion_reportes.persistence.crud.CrudPersonalEntity;
import org.rsosa.gestion_reportes.persistence.entity.Personal;
import org.rsosa.gestion_reportes.web.mapper.PersonalMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonalEntityRepository implements PersonalRepository {
    private final CrudPersonalEntity crudPersonalEntity;
    private final PersonalMapper personalMapper;

    public PersonalEntityRepository(CrudPersonalEntity crudPersonalEntity, PersonalMapper personalMapper) {
        this.crudPersonalEntity = crudPersonalEntity;
        this.personalMapper = personalMapper;
    }
    @Override
    public List<PersonalDto> obtenerTodo() {
        return this.personalMapper.toDto((List<Personal>) this.crudPersonalEntity.findAll());
    }

    @Override
    public PersonalDto obtenerPersonalPorId(Long id) {
        return this.personalMapper.toDto(this.crudPersonalEntity.findById(id).orElse(null));
    }
    @Override
    public PersonalDto guardarPersonal(PersonalDto personalDto) {
        Personal personal = this.personalMapper.toEntity(personalDto);
        return this.personalMapper.toDto(this.crudPersonalEntity.save(personal));
    }
    @Override
    public PersonalDto actualizarPersonal(Long id, PersonalDto personalDto) {
        Personal personal = this.crudPersonalEntity.findById(id).orElse(null);
        if (personal != null) {
            this.personalMapper.updateEntityFromDto(personalDto, personal);
            return this.personalMapper.toDto(this.crudPersonalEntity.save(personal));
        }
        return null;
    }
        @Override
        public void eliminarPersonal(Long id) {
            this.crudPersonalEntity.deleteById(id);
        }
}
