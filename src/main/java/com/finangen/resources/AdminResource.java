package com.finangen.resources;

import com.finangen.domains.Admin;
import com.finangen.domains.dtos.AdminDTO;
import com.finangen.services.AdminService;
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
@RequestMapping(value = "/admin")
@Tag(name = "Admin", description = "API para Gerenciamento de Admins")
public class AdminResource {

    @Autowired
    private AdminService adminService;

    @GetMapping
    @Operation(summary = "Listar todos os usuários",
            description = "Retorna uma lista com todos os usuarios cadastrados")
    public ResponseEntity<List<AdminDTO>> findAll(){
        return ResponseEntity.ok().body(adminService.findAll());
    }

    @GetMapping(value="/{id}")
    @Operation(summary = "Buscar uma Categoria pelo ID",
            description = "Realiza busca de uma Categoria pelo ID")
    public ResponseEntity<AdminDTO> findById(@PathVariable Long id){
        Admin obj = this.adminService.findById(id);
        return ResponseEntity.ok().body(new AdminDTO(obj));
    }

    @GetMapping(value="/cpf/{cpf}")
    @Operation(summary = "Buscar um Admin pelo CPF",
            description = "Realiza busca de um Admin pelo CPF")
    public ResponseEntity<AdminDTO> findByCpf(@PathVariable String cpf){
        Admin obj = this.adminService.findByCpf(cpf);
        return ResponseEntity.ok().body(new AdminDTO(obj));
    }

    @GetMapping(value="/email/{email}")
    @Operation(summary = "Buscar um Admin pelo email",
            description = "Realiza busca de um Admin pelo email")
    public ResponseEntity<AdminDTO> findByEmail(@PathVariable String email){
        Admin obj = this.adminService.findByEmail(email);
        return ResponseEntity.ok().body(new AdminDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Cria um novo Admin",
            description = "Cria um novo Admin com base nos dados fornecidos")
    public ResponseEntity<AdminDTO> create(@Valid @RequestBody AdminDTO dto){
        Admin admin = adminService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(admin.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{id}")
    @Operation(summary = "Altera um Admin",
            description = "Altera um Admin existente")
    public ResponseEntity<AdminDTO> update(@PathVariable Long id, @Valid @RequestBody AdminDTO objDto){
        Admin Obj = adminService.update(id, objDto);
        return ResponseEntity.ok().body(new AdminDTO(Obj));
    }

    @DeleteMapping(value="/{id}")
    @Operation(summary = "Deleta um Admin",
            description = "Deleta um Admin a partir do seu ID")
    public ResponseEntity<AdminDTO> delete(@PathVariable Long id){
        adminService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
