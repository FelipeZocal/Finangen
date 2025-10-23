package com.finangen.resources;

import com.finangen.domains.Banco;
import com.finangen.domains.dtos.BancoDTO;
import com.finangen.services.BancoService;
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
@RequestMapping(value = "/banco")
@Tag(name = "Banco", description = "API para Gerenciamento de Banco")
public class BancoResource {

    @Autowired
    private BancoService bancoService;

    @GetMapping
    @Operation(summary = "Listar todos os Bancos",
            description = "Retorna uma lista com todos os Bancos cadastrados")
    public ResponseEntity<List<BancoDTO>> findAll(){
        return ResponseEntity.ok().body(bancoService.findAll());
    }

    @GetMapping(value="/{idBanco}")
    @Operation(summary = "Buscar um Banco pelo ID",
            description = "Realiza busca de um Banco pelo ID")
    public ResponseEntity<BancoDTO> findByIdBanco(@PathVariable Long idBanco){
        Banco obj = this.bancoService.findByIdBanco(idBanco);
        return ResponseEntity.ok().body(new BancoDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Cria um novo Banco",
            description = "Cria um novo Banco com base nos dados fornecidos")
    public ResponseEntity<BancoDTO> create(@Valid @RequestBody BancoDTO dto){
        Banco banco = bancoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idBanco}")
                .buildAndExpand(banco.getIdBanco()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{idBanco}")
    @Operation(summary = "Altera um Banco",
            description = "Altera um Banco existente")
    public ResponseEntity<BancoDTO> update(@PathVariable Long idBanco, @Valid @RequestBody BancoDTO objDto){
        Banco Obj = bancoService.update(idBanco, objDto);
        return ResponseEntity.ok().body(new BancoDTO(Obj));
    }

    @DeleteMapping(value="/{idBanco}")
    @Operation(summary = "Deleta um Banco",
            description = "Deleta um Banco a partir do seu ID")
    public ResponseEntity<BancoDTO> delete(@PathVariable Long idBanco){
        bancoService.delete(idBanco);
        return ResponseEntity.noContent().build();
    }

}
