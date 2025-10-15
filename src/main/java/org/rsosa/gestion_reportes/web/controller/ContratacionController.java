package org.rsosa.gestion_reportes.web.controller;

import jakarta.validation.Valid;
import org.rsosa.gestion_reportes.dominio.dto.ContratacionDto;
import org.rsosa.gestion_reportes.dominio.exception.CatalogoInvalidoException;
import org.rsosa.gestion_reportes.persistence.ContratacionEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/contrataciones")
public class ContratacionController {

    private final ContratacionEntityRepository repository;

    public ContratacionController(ContratacionEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<ContratacionDto>> obtenerTodo() {
        return ResponseEntity.ok(this.repository.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorCodigo(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.repository.obtenerPorCodigo(id));
        } catch (CatalogoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/municipalidad/{codigoMunicipalidad}")
    public ResponseEntity<List<ContratacionDto>> obtenerPorMunicipalidad(@PathVariable Long codigoMunicipalidad) {
        return ResponseEntity.ok(this.repository.obtenerPorMunicipalidad(codigoMunicipalidad));
    }

    @PostMapping
    public ResponseEntity<?> guardarContratacion(@RequestBody @Valid ContratacionDto contratacionDto) {
        try {
            ContratacionDto resultado = this.repository.guardarContratacion(contratacionDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (CatalogoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarContratacion(@PathVariable Long id, @RequestBody @Valid ContratacionDto contratacionDto) {
        try {
            ContratacionDto resultado = this.repository.actualizarContratacion(id, contratacionDto);
            return ResponseEntity.ok(resultado);
        } catch (CatalogoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarContratacion(@PathVariable Long id) {
        try {
            this.repository.eliminarContratacion(id);
            return ResponseEntity.noContent().build();
        } catch (CatalogoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}