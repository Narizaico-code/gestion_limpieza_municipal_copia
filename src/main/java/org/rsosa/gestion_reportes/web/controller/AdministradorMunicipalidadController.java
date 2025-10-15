package org.rsosa.gestion_reportes.web.controller;

import jakarta.validation.Valid;
import org.rsosa.gestion_reportes.dominio.dto.AdministradorMunicipalidadDto;
import org.rsosa.gestion_reportes.dominio.dto.AsignarAdminDto;
import org.rsosa.gestion_reportes.dominio.exception.CatalogoInvalidoException;
import org.rsosa.gestion_reportes.persistence.AdministradorMunicipalidadEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/admin-municipalidad")
public class AdministradorMunicipalidadController {

    private final AdministradorMunicipalidadEntityRepository repository;

    public AdministradorMunicipalidadController(AdministradorMunicipalidadEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<AdministradorMunicipalidadDto>> obtenerTodo() {
        return ResponseEntity.ok(this.repository.obtenerTodo());
    }

    @GetMapping("/municipalidad/{codigoMunicipalidad}")
    public ResponseEntity<?> obtenerPorMunicipalidad(@PathVariable Long codigoMunicipalidad) {
        var resultado = this.repository.obtenerPorMunicipalidad(codigoMunicipalidad);
        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        } else {
            return new ResponseEntity<>("No hay administrador asignado a esta municipalidad.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/administrador/{codigoAdmin}")
    public ResponseEntity<List<AdministradorMunicipalidadDto>> obtenerPorAdministrador(@PathVariable Long codigoAdmin) {
        return ResponseEntity.ok(this.repository.obtenerPorAdministrador(codigoAdmin));
    }

    @PostMapping
    public ResponseEntity<?> asignarAdminAMunicipalidad(@RequestBody @Valid AsignarAdminDto asignarDto) {
        try {
            AdministradorMunicipalidadDto resultado = this.repository.asignarAdminAMunicipalidad(
                    asignarDto.admin_id(),
                    asignarDto.municipalidad_id()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (CatalogoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigoAdminMunicipalidad}")
    public ResponseEntity<?> desasignarAdmin(@PathVariable Long codigoAdminMunicipalidad) {
        try {
            this.repository.desasignarAdminDeMunicipalidad(codigoAdminMunicipalidad);
            return ResponseEntity.noContent().build();
        } catch (CatalogoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}