package com.finangen.resources;

import com.finangen.domains.ContaBancaria;
import com.finangen.domains.dtos.ContaBancariaDTO;
import com.finangen.services.ContaBancariaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/contabancaria")
@Tag(name = "Conta Bancaria", description = "API para Gerenciamento de Conta Bancaria")
public class ContaBancariaResource {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @GetMapping
    @Operation(summary = "Listar todos as Contas Bancarias",
            description = "Retorna uma lista com todos os Contas cadastradas")
    public ResponseEntity<List<ContaBancariaDTO>> findAll(){
        return ResponseEntity.ok().body(contaBancariaService.findAll());
    }

    @GetMapping(value="/{idConta}")
    @Operation(summary = "Buscar uma Conta Bancaria pelo ID",
            description = "Realiza busca de uma Conta Bancaria pelo ID")
    public ResponseEntity<ContaBancariaDTO> findByIdConta(@PathVariable Long idConta){
        ContaBancaria obj = this.contaBancariaService.findByIdConta(idConta);
        return ResponseEntity.ok().body(new ContaBancariaDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Cria uma novo Conta Bancaria",
            description = "Cria uma novo Conta Bancaria com base nos dados fornecidos")
    public ResponseEntity<ContaBancariaDTO> create(@Valid @RequestBody ContaBancariaDTO dto){
        ContaBancaria contaBancaria = contaBancariaService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idConta}")
                .buildAndExpand(contaBancaria.getIdConta()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{idConta}")
    @Operation(summary = "Altera um Conta Bancaria",
            description = "Altera um Conta Bancaria existente")
    public ResponseEntity<ContaBancariaDTO> update(@PathVariable Long idConta, @Valid @RequestBody ContaBancariaDTO objDto){
        ContaBancaria Obj = contaBancariaService.update(idConta, objDto);
        return ResponseEntity.ok().body(new ContaBancariaDTO(Obj));
    }

    @DeleteMapping(value="/{idConta}")
    @Operation(summary = "Deleta uma Conta Bancaria",
            description = "Deleta uma Conta Bancaria a partir do seu ID")
    public ResponseEntity<ContaBancariaDTO> delete(@PathVariable Long idConta){
        contaBancariaService.delete(idConta);
        return ResponseEntity.noContent().build();
    }

}
