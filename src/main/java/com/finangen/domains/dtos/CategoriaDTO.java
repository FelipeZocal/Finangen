package com.finangen.domains.dtos;

import com.finangen.domains.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoriaDTO {

    private Long idCategoria;

    @NotNull(message = "O campo Descrição não pode ser vazio")
    @NotBlank(message = "O campo Descrição não pode ser nulo")
    private String descricaoCategoria;

    @NotNull(message = "Campo Pessoa é obrigatório")
    private Long id;
    private String nome;
    private String cpf;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria categoria) {
        this.idCategoria = categoria.getIdCategoria();
        this.descricaoCategoria = categoria.getDescricaoCategoria();

        this.id = categoria.getPessoa().getId();
        this.nome = categoria.getPessoa().getNome();
        this.cpf = categoria.getPessoa().getCpf();
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public @NotNull(message = "O campo Descrição não pode ser vazio") @NotBlank(message = "O campo Descrição não pode ser nulo") String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(@NotNull(message = "O campo Descrição não pode ser vazio") @NotBlank(message = "O campo Descrição não pode ser nulo") String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    public @NotNull(message = " ") Long getId() {
        return id;
    }

    public void setId(@NotNull(message = " ") Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
