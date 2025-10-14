package org.rsosa.gestion_reportes.web.controller;

import org.rsosa.gestion_reportes.dominio.service.ContratacionService;
import org.rsosa.gestion_reportes.dominio.dto.ContratacionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/contrataciones")
public class ContratacionController {
    private final ContratacionService contratacionService;

    public ContratacionController(ContratacionService contratacionService) {
        this.contratacionService = contratacionService;
    }

    @GetMapping
    public ResponseEntity<List<ContratacionDto>> obtenerTodo() {
        return ResponseEntity.ok(this.contratacionService.obtenerTodo());
    }

    @GetMapping("{id}")
    public ResponseEntity<ContratacionDto> obtenerContratacionPorId(@PathVariable Long id) {
        var contratacion = this.contratacionService.obtenerContratacionPorId(id);
        if (contratacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contratacion);
    }

    @PostMapping
    public ResponseEntity<ContratacionDto> guardarContratacion(@RequestBody ContratacionDto contratacionDto) {
        var contratacionGuardada = this.contratacionService.guardarContratacion(contratacionDto);
        return ResponseEntity.ok(contratacionGuardada);
    }

    @PutMapping("{id}")
    public ResponseEntity<ContratacionDto> actualizarContratacion(@PathVariable Long id, @RequestBody ContratacionDto contratacionDto) {
        var contratacionActualizada = this.contratacionService.actualizarContratacion(id, contratacionDto);
        if (contratacionActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contratacionActualizada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarContratacion(@PathVariable Long id) {
        this.contratacionService.eliminarContratacion(id);
        return ResponseEntity.noContent().build();
    }
}
