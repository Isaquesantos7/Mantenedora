package com.mantenedora.program.controllers;

import com.mantenedora.program.DTO.MantenedoraDTO;
import com.mantenedora.program.model.Mantenedora;
import com.mantenedora.program.services.MantenedoraService;
import com.mantenedora.program.services.exception.ObjectNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Lista todas as mantenedoras cadastradas.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Listando todas as mantenedoras cadastradas.")
            }
    )
    @GetMapping("/api/mantenedora")
    public ResponseEntity<List<Mantenedora>> findAll() {
        List<Mantenedora> lista = this.mantenedoraService.listaMantenedora();

        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @Operation(description = "Buscar mantenedora cadastrada por ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Traz a mantenedora cadastrada caso haja uma mantenedora cadastrada com o ID informado."),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Caso não encontre mantenedora cadastrada com o ID informado.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjectNotFoundException.class))
                    )
            }
    )
    @GetMapping("/api/mantenedora/{id}")
    public ResponseEntity<Mantenedora> findById(@PathVariable Integer id) {
        Mantenedora mantenedora = this.mantenedoraService.buscarMantenedora(id);

        return ResponseEntity.status(HttpStatus.OK).body(mantenedora);
    }

    @Operation(description = "Cadastra mantenedora.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastrando mantenedora."),
                    @ApiResponse(responseCode = "500", description = "Error ao cadastrar mantenedora.")
            }
    )
    @PostMapping("/api/mantenedora")
    public ResponseEntity<Mantenedora> save(@RequestBody @Validated MantenedoraDTO mantenedoraDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(this.mantenedoraService.cadastrarMantenedora(mantenedoraDTO));
    }

    @Operation(description = "Atualiza mantenedora cadastrada por ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza a mantenedora cadastrada caso haja uma mantenedora cadastrada com o ID informado."),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Caso não encontre mantenedora cadastrada com o ID informado.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjectNotFoundException.class))
                    )
            }
    )
    @PutMapping("/api/mantenedora/{id}")
    public ResponseEntity<Mantenedora> update(@PathVariable Integer id, @RequestBody MantenedoraDTO mantenedoraDTO){
        Mantenedora mantenedora = this.mantenedoraService.atualizarMantenedora(id, mantenedoraDTO);

        return ResponseEntity.status(HttpStatus.OK).body(mantenedora);
    }

    @Operation(description = "Buscar mantenedora cadastrada por ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta mantenedora cadastrada caso haja uma mantenedora cadastrada com o ID informado."),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Caso não encontre mantenedora cadastrada com o ID informado.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjectNotFoundException.class))
                    )
            }
    )
    @DeleteMapping("/api/mantenedora/{id}")
    public ResponseEntity<Mantenedora> delete(@PathVariable Integer id) {
        Mantenedora mantenedora = this.mantenedoraService.deletaMantenedora(id);

        return ResponseEntity.status(HttpStatus.OK).body(mantenedora);
    }
}
