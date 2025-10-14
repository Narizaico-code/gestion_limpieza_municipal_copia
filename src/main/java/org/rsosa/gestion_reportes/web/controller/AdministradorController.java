package org.rsosa.gestion_reportes.web.controller;

import org.rsosa.gestion_reportes.dominio.dto.AdministradorDto;
import org.rsosa.gestion_reportes.dominio.service.AdministradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/administradores")
public class AdministradorController {
    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping
    public ResponseEntity<List<AdministradorDto>> obtenerTodo() {
        return ResponseEntity.ok(this.administradorService.obtenerTodo());
    }

    @GetMapping("{id}")
    public ResponseEntity<AdministradorDto> obtenerAdministradorPorId(Long id) {
        return ResponseEntity.ok(this.administradorService.obtenerAdministradorPorId(id));
    }
    @PostMapping
    public ResponseEntity<AdministradorDto> guardarAdministrador(@RequestBody AdministradorDto administradorDto){
        return ResponseEntity.ok(this.administradorService.guardarAdministrador(administradorDto));
    }
    @PutMapping("{id}")
    public ResponseEntity<AdministradorDto> actualizarAdministrador(@PathVariable Long id, @RequestBody AdministradorDto administradorDto){
        return ResponseEntity.ok(this.administradorService.actualizarAdministrador(id, administradorDto));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<AdministradorDto> eliminarAdministrador(Long id){
        this.administradorService.eliminarAdministrador(id);
        return ResponseEntity.ok().build();
    }

}
