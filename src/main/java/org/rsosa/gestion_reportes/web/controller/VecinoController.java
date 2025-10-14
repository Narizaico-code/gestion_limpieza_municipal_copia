package org.rsosa.gestion_reportes.web.controller;


import jakarta.validation.Valid;
import org.rsosa.gestion_reportes.dominio.dto.VecinoDto;
import org.rsosa.gestion_reportes.dominio.service.VecinoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vecinos")
public class VecinoController {
    private final VecinoService vecinoService;

    public VecinoController(VecinoService vecinoService) {
        this.vecinoService = vecinoService;
    }

    @GetMapping
    public ResponseEntity<List<VecinoDto>> obtenerTodo(){
        return ResponseEntity.ok(this.vecinoService.obtenerTodo());
    }

    @GetMapping("{id}")
    public ResponseEntity<VecinoDto> obtenerVecinoPorCodigo(@PathVariable Long id){
        return ResponseEntity.ok(this.vecinoService.obtenerVecinoPorCodigo(id));
    }

    @PostMapping
    public ResponseEntity<VecinoDto> guardarVecino(@RequestBody @Valid VecinoDto vecinoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.vecinoService.guardarVecino(vecinoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VecinoDto> actualizarVecino(@PathVariable Long id, @RequestBody @Valid VecinoDto vecinoDto){
        return ResponseEntity.ok(this.vecinoService.actualizarVecino(id, vecinoDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<VecinoDto> eliminarVecino(@PathVariable Long id){
        this.vecinoService.eliminarVecino(id);
        return ResponseEntity.ok().build();
    }
}