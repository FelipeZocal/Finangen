package com.finangen.resources;

import com.finangen.domains.Categoria;
import com.finangen.domains.dtos.CategoriaDTO;
import com.finangen.services.CategoriaService;
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
@RequestMapping(value = "/categoria")
@Tag(name = "Categoria", description = "API para Gerenciamento de Categoria")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Listar todos as categorias",
            description = "Retorna uma lista com todos as categorias cadastrados")
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        return ResponseEntity.ok().body(categoriaService.findAll());
    }

    @GetMapping(value="/{idCategoria}")
    @Operation(summary = "Buscar uma Categoria pelo ID",
            description = "Realiza busca de uma Categoria pelo ID")
    public ResponseEntity<CategoriaDTO> findByIdCategoria(@PathVariable Long idCategoria){
        Categoria obj = this.categoriaService.findByIdCategoria(idCategoria);
        return ResponseEntity.ok().body(new CategoriaDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Cria uma nova Categoria",
            description = "Cria uma nova Categoria com base nos dados fornecidos")
    public ResponseEntity<CategoriaDTO> create(@Valid @RequestBody CategoriaDTO dto){
        Categoria categoria = categoriaService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCategoria}")
                .buildAndExpand(categoria.getIdCategoria()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{idCategoria}")
    @Operation(summary = "Altera uma Categoria",
            description = "Altera uma Categoria existente")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long idCategoria, @Valid @RequestBody CategoriaDTO objDto){
        Categoria Obj = categoriaService.update(idCategoria, objDto);
        return ResponseEntity.ok().body(new CategoriaDTO(Obj));
    }

    @DeleteMapping(value = "/{idCategoria}")
    @Operation(summary = "Deleta uma Categoria",
            description = "Deleta uma Categoria a partir do seu ID")
    public ResponseEntity<CategoriaDTO> delete(@PathVariable Long idCategoria){
        categoriaService.delete(idCategoria);
        return ResponseEntity.noContent().build();
    }

}
