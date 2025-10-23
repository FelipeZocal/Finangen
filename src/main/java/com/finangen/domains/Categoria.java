package com.finangen.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finangen.domains.dtos.CategoriaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_categoria")
    private Long idCategoria;

    @NotNull
    @NotBlank
    private String descricaoCategoria;

    @ManyToOne
    @JoinColumn(name = "idpessoa")
    private Pessoa pessoa;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Lancamento> lancamentos = new ArrayList<>();

    public Categoria() {
    }

    public Categoria(Long idCategoria, String descricaoCategoria, Pessoa pessoa) {
        this.idCategoria = idCategoria;
        this.descricaoCategoria = descricaoCategoria;
        this.pessoa = pessoa;
    }

    public Categoria(CategoriaDTO dto) {
        this.idCategoria = dto.getIdCategoria();
        this.descricaoCategoria = dto.getDescricaoCategoria();
        this.pessoa = new Usuario();
        this.pessoa.setId(dto.getId());
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public @NotNull @NotBlank String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(@NotNull @NotBlank String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Lancamento> getLancamentos(){
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(idCategoria, categoria.idCategoria);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idCategoria);
    }
}
