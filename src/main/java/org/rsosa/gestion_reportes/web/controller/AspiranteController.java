package org.rsosa.gestion_reportes.web.controller;

import org.rsosa.gestion_reportes.dominio.dto.AspiranteDto;
import org.rsosa.gestion_reportes.dominio.service.AdministradorService;
import org.rsosa.gestion_reportes.dominio.service.AspiranteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/aspirantes")
public class AspiranteController {
    private AspiranteService aspiranteService;
    public AspiranteController(AspiranteService aspiranteService) {
        this.aspiranteService = aspiranteService;
    }
    @GetMapping
    public ResponseEntity<List<AspiranteDto>> obtenerTodo() {
        return ResponseEntity.ok(this.aspiranteService.obtenerTodo());
    }
    @GetMapping("{id}")
    public ResponseEntity<AspiranteDto> obtenerAspirantePorId(Long id) {
        var aspirante = this.aspiranteService.obtenerAspirantePorId(id);
        if (aspirante == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aspirante);
    }
    @PostMapping
    public ResponseEntity<AspiranteDto> guardarAspirante(AspiranteDto aspiranteDto) {
        var aspirante = this.aspiranteService.guardarAspirante(aspiranteDto);
        return ResponseEntity.ok(aspirante);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AspiranteDto> actualizarAspirante(@PathVariable Long id, @RequestBody AspiranteDto aspiranteDto) {
        var aspirante = this.aspiranteService.actualizarAspirante(id, aspiranteDto);
        if (aspirante == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aspirante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAspirante(@PathVariable Long id) {
        this.aspiranteService.eliminarAspirante(id);
        return ResponseEntity.noContent().build();

    }
}
