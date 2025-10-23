package com.finangen.resources;

import com.finangen.domains.Banco;
import com.finangen.domains.Lancamento;
import com.finangen.domains.Lancamento;
import com.finangen.domains.dtos.BancoDTO;
import com.finangen.domains.dtos.LancamentoDTO;
import com.finangen.services.LancamentoService;
import com.finangen.services.LancamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/lancamento")
@Tag(name = "Lancamento", description = "API para Gerenciamento de Lancamento")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    @Operation(summary = "Listar todos os usuários",
            description = "Retorna uma lista com todos os usuarios cadastrados")
    public ResponseEntity<List<LancamentoDTO>> findAll(){
        return ResponseEntity.ok().body(lancamentoService.findAll());
    }

    @GetMapping(value = "/{idLancamento}")
    @Operation(summary = "Buscar um Banco pelo ID",
            description = "Realiza busca de um Banco pelo ID")
    public ResponseEntity<LancamentoDTO> findByIdLancamento(@PathVariable Long idLancamento){
        Lancamento obj = this.lancamentoService.findByIdLancamento(idLancamento);
        return ResponseEntity.ok().body(new LancamentoDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Cria um novo Lancamento",
            description = "Cria um novo Lancamento com base nos dados fornecidos")
    public ResponseEntity<LancamentoDTO> create(@Valid @RequestBody LancamentoDTO dto){
        Lancamento lancamento = lancamentoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idLancamento}")
                .buildAndExpand(lancamento.getIdLancamento()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{idLancamento}")
    @Operation(summary = "Altera um Lancamento",
            description = "Altera um Lancamento existente")
    public ResponseEntity<LancamentoDTO> update(@PathVariable Long idLancamento, @Valid @RequestBody LancamentoDTO objDto){
        Lancamento Obj = lancamentoService.update(idLancamento, objDto);
        return ResponseEntity.ok().body(new LancamentoDTO(Obj));
    }

    @DeleteMapping(value="/{idLancamento}")
    @Operation(summary = "Deleta um Lancamento",
            description = "Deleta um Lancamento a partir do seu ID")
    public ResponseEntity<LancamentoDTO> delete(@PathVariable Long idLancamento){
        lancamentoService.delete(idLancamento);
        return ResponseEntity.noContent().build();
    }

}
