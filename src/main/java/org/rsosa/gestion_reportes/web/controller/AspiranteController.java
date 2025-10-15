package org.rsosa.gestion_reportes.web.controller;

import jakarta.validation.Valid;
import org.rsosa.gestion_reportes.dominio.dto.AspiranteDto;
import org.rsosa.gestion_reportes.dominio.exception.CatalogoInvalidoException;
import org.rsosa.gestion_reportes.persistence.AspiranteEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/aspirantes")
public class AspiranteController {

    private final AspiranteEntityRepository repository;

    public AspiranteController(AspiranteEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<AspiranteDto>> obtenerTodo() {
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

    @GetMapping("/contratacion/{codigoContratacion}")
    public ResponseEntity<List<AspiranteDto>> obtenerPorContratacion(@PathVariable Long codigoContratacion) {
        return ResponseEntity.ok(this.repository.obtenerPorContratacion(codigoContratacion));
    }

    @PostMapping
    public ResponseEntity<?> guardarAspirante(@RequestBody @Valid AspiranteDto aspiranteDto) {
        try {
            AspiranteDto resultado = this.repository.guardarAspirante(aspiranteDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (CatalogoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAspirante(@PathVariable Long id, @RequestBody @Valid AspiranteDto aspiranteDto) {
        try {
            AspiranteDto resultado = this.repository.actualizarAspirante(id, aspiranteDto);
            return ResponseEntity.ok(resultado);
        } catch (CatalogoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAspirante(@PathVariable Long id) {
        try {
            this.repository.eliminarAspirante(id);
            return ResponseEntity.noContent().build();
        } catch (CatalogoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/contratar/{codigoAspirante}")
    public ResponseEntity<?> contratarAspirante(@PathVariable Long codigoAspirante) {
        try {
            AspiranteDto resultado = this.repository.contratarAspirante(codigoAspirante);
            return ResponseEntity.ok(resultado);
        } catch (CatalogoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}