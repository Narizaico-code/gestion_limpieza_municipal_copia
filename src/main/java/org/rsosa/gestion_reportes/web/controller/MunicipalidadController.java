package org.rsosa.gestion_reportes.web.controller;

import org.rsosa.gestion_reportes.dominio.dto.MunicipalidadDto;
import org.rsosa.gestion_reportes.dominio.service.MunicipalidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/municipalidades")
public class MunicipalidadController {

    private final MunicipalidadService municipalidadService;

    public MunicipalidadController(MunicipalidadService municipalidadService) {
        this.municipalidadService = municipalidadService;
    }

    @GetMapping
    public ResponseEntity<List<MunicipalidadDto>> obtenerTodo() {
        return ResponseEntity.ok(municipalidadService.obtenerTodo());
    }

    @GetMapping("{id}")
    public ResponseEntity<MunicipalidadDto> obtenerMunicipalidadPorId(@PathVariable Long id) {
        var municipalidad = municipalidadService.obtenerMunicipalidadPorId(id);
        if (municipalidad == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(municipalidad);
    }

    @PostMapping
    public ResponseEntity<MunicipalidadDto> guardarMunicipalidad(@RequestBody MunicipalidadDto municipalidadDto) {
        var guardada = municipalidadService.guardarMunicipalidad(municipalidadDto);
        return ResponseEntity.ok(guardada);
    }

    @PutMapping("{id}")
    public ResponseEntity<MunicipalidadDto> actualizarMunicipalidad(@PathVariable Long id, @RequestBody MunicipalidadDto municipalidadDto) {
        var actualizada = municipalidadService.actualizarMunicipalidad(id, municipalidadDto);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarMunicipalidad(@PathVariable Long id) {
        municipalidadService.eliminarMunicipalidad(id);
        return ResponseEntity.noContent().build();
    }
}
