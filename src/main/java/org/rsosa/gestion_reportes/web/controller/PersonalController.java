package org.rsosa.gestion_reportes.web.controller;

import jakarta.validation.Valid;
import org.rsosa.gestion_reportes.dominio.dto.PersonalDto;
import org.rsosa.gestion_reportes.dominio.dto.TipoReporteDto;
import org.rsosa.gestion_reportes.dominio.service.PersonalService;
import org.rsosa.gestion_reportes.dominio.service.TipoReporteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/personal")
public class PersonalController {
    private PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }
    @GetMapping
 public ResponseEntity<List<PersonalDto>> obtenerTodo(){
    return ResponseEntity.ok(this.personalService.obtenerTodo());
}
    @GetMapping("{id}")
    public ResponseEntity<?> obtenerPersonalPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.personalService.obtenerPersonalPorId(id));
    }
    @PostMapping
    public ResponseEntity<PersonalDto> crear(@Valid @RequestBody PersonalDto personalDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.personalService.guardarPersonal(personalDto));
    }
    @PutMapping("{id}")
    public ResponseEntity<PersonalDto> actualizar(@PathVariable Long id, @RequestBody PersonalDto personalDto) {
        return ResponseEntity.ok(this.personalService.actualizarPersonal(id, personalDto));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<PersonalDto> eliminar(@PathVariable Long id) {
        this.personalService.eliminarPersonal(id);
        return ResponseEntity.ok().build();
    }
}