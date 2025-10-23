package com.finangen.resources;

import com.finangen.domains.Usuario;
import com.finangen.domains.dtos.UsuarioDTO;
import com.finangen.services.UsuarioService;
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
@RequestMapping(value = "/usuario")
@Tag(name = "Usuario", description = "API para Gerenciamento de Usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listar todos os usuários",
            description = "Retorna uma lista com todos os usuarios cadastrados")
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        return ResponseEntity.ok().body(usuarioService.findAll());
    }

    @GetMapping(value="/{id}")
    @Operation(summary = "Buscar um Banco pelo ID",
            description = "Realiza busca de um Banco pelo IDl")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
        Usuario obj = this.usuarioService.findById(id);
        return ResponseEntity.ok().body(new UsuarioDTO(obj));
    }

    @GetMapping(value="/cpf/{cpf}")
    @Operation(summary = "Buscar um Usuario pelo CPF",
            description = "Realiza busca de um Usuario pelo CPF")
    public ResponseEntity<UsuarioDTO> findByCpf(@PathVariable String cpf){
        Usuario obj = this.usuarioService.findByCpf(cpf);
        return ResponseEntity.ok().body(new UsuarioDTO(obj));
    }

    @GetMapping(value="/email/{email}")
    @Operation(summary = "Buscar um Cliente pelo email",
            description = "Realiza busca de um Cliente pelo email")
    public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable String email){
        Usuario obj = this.usuarioService.findByEmail(email);
        return ResponseEntity.ok().body(new UsuarioDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Cria um novo usuário",
            description = "Cria um novo usuario com base nos dados fornecidos")
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO dto){
        Usuario usuario = usuarioService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{id}")
    @Operation(summary = "Altera um usuario",
            description = "Altera um usuario existente")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO objDto){
        Usuario Obj = usuarioService.update(id, objDto);
        return ResponseEntity.ok().body(new UsuarioDTO(Obj));
    }

    @DeleteMapping(value="/{id}")
    @Operation(summary = "Deleta um usuario",
            description = "Deleta um usuario a partir do seu ID")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
