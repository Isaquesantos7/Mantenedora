package com.mantenedora.program.controllers;

import com.mantenedora.program.DTO.MantenedoraDTO;
import com.mantenedora.program.model.Mantenedora;
import com.mantenedora.program.services.MantenedoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MantenedoraController {

    @Autowired
    private MantenedoraService mantenedoraService;

    @GetMapping("/api/mantenedora")
    public ResponseEntity<List<Mantenedora>> findAll() {
        List<Mantenedora> lista = this.mantenedoraService.listaMantenedora();

        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/api/mantenedora/{id}")
    public ResponseEntity<Mantenedora> findById(@PathVariable Integer id) {
        Mantenedora mantenedora = this.mantenedoraService.buscarMantenedora(id);

        return ResponseEntity.status(HttpStatus.OK).body(mantenedora);
    }

    @PostMapping("/api/mantenedora")
    public ResponseEntity<Mantenedora> save(@RequestBody @Validated MantenedoraDTO mantenedoraDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(this.mantenedoraService.cadastrarMantenedora(mantenedoraDTO));
    }

    @PutMapping("/api/mantenedora/{id}")
    public ResponseEntity<Mantenedora> update(@PathVariable Integer id, @RequestBody MantenedoraDTO mantenedoraDTO){
        Mantenedora mantenedora = this.mantenedoraService.atualizarMantenedora(id, mantenedoraDTO);

        return ResponseEntity.status(HttpStatus.OK).body(mantenedora);
    }

    @DeleteMapping("/api/mantenedora/{id}")
    public ResponseEntity<Mantenedora> delete(@PathVariable Integer id) {
        Mantenedora mantenedora = this.mantenedoraService.deletaMantenedora(id);

        return ResponseEntity.status(HttpStatus.OK).body(mantenedora);
    }
}
