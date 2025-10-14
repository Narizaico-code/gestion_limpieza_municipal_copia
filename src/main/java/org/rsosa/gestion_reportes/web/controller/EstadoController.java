package org.rsosa.gestion_reportes.web.controller;


import jakarta.validation.Valid;
import org.rsosa.gestion_reportes.dominio.dto.EstadoDto;
import org.rsosa.gestion_reportes.dominio.service.EstadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/estados")
public class EstadoController {
    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoDto>> obtenerTodo(){
        return ResponseEntity.ok(this.estadoService.obtenerTodo());
    }

    @GetMapping("/buscarPorCodigo/{id}")
    public ResponseEntity<EstadoDto> obtenerEstadoPorCodigo(@PathVariable Long id){
        return ResponseEntity.ok(this.estadoService.obtenerEstadoPorCodigo(id));
    }

    @GetMapping("/buscarPorNombre/{nombre}")
    public ResponseEntity<EstadoDto> obtenerEstadoPorNombre(@PathVariable String nombre){
        return ResponseEntity.ok(this.estadoService.obtenerEstadoPorNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<EstadoDto> guardarEstado(@RequestBody @Valid EstadoDto estadoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.estadoService.guardarEstado(estadoDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<EstadoDto> actualizarEstado(@PathVariable Long id, @RequestBody EstadoDto estadoDto){
        return ResponseEntity.ok(this.estadoService.actualizarEstado(id, estadoDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EstadoDto> eliminarEstado(@PathVariable Long id){
        this.estadoService.eliminarEstado(id);
        return ResponseEntity.ok().build();
    }
}