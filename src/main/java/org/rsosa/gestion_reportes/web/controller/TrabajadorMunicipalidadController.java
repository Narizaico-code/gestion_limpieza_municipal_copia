package org.rsosa.gestion_reportes.web.controller;

import jakarta.validation.Valid;
import org.rsosa.gestion_reportes.dominio.dto.TrabajadorMunicipalDto;
import org.rsosa.gestion_reportes.dominio.exception.CatalogoInvalidoException;
import org.rsosa.gestion_reportes.persistence.TrabajadorMunicipalidadEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/v1/trabajadores-municipales")
public class TrabajadorMunicipalidadController {

    private final TrabajadorMunicipalidadEntityRepository repository;

    public TrabajadorMunicipalidadController(TrabajadorMunicipalidadEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<TrabajadorMunicipalDto>> obtenerTodo() {
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

    @GetMapping("/personal/{codigoPersonal}")
    public ResponseEntity<List<TrabajadorMunicipalDto>> obtenerPorPersonal(@PathVariable Long codigoPersonal) {
        return ResponseEntity.ok(this.repository.obtenerPorPersonal(codigoPersonal));
    }

    @GetMapping("/municipalidad/{codigoMunicipalidad}")
    public ResponseEntity<List<TrabajadorMunicipalDto>> obtenerPorMunicipalidad(@PathVariable Long codigoMunicipalidad) {
        return ResponseEntity.ok(this.repository.obtenerPorMunicipalidad(codigoMunicipalidad));
    }

    @GetMapping("/zona/{zona}")
    public ResponseEntity<List<TrabajadorMunicipalDto>> obtenerPorZona(@PathVariable String zona) {
        return ResponseEntity.ok(this.repository.obtenerPorZona(zona));
    }

    @PostMapping
    public ResponseEntity<?> guardarTrabajadorMunicipalidad(@RequestBody @Valid TrabajadorMunicipalDto trabajadorMunicipalDto) {
        try {
            TrabajadorMunicipalDto resultado = this.repository.guardarTrabajadorMunicipalidad(trabajadorMunicipalDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (CatalogoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTrabajadorMunicipalidad(@PathVariable Long id, @RequestBody @Valid TrabajadorMunicipalDto trabajadorMunicipalDto) {
        try {
            TrabajadorMunicipalDto resultado = this.repository.actualizarTrabajadorMunicipalidad(id, trabajadorMunicipalDto);
            return ResponseEntity.ok(resultado);
        } catch (CatalogoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTrabajadorMunicipalidad(@PathVariable Long id) {
        try {
            this.repository.eliminarTrabajadorMunicipalidad(id);
            return ResponseEntity.noContent().build();
        } catch (CatalogoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}