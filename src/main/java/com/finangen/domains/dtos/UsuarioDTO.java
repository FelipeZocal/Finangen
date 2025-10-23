package com.finangen.domains.dtos;

import com.finangen.domains.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import com.finangen.domains.enums.TipoPessoa;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioDTO {

    protected Long id;

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank(message = "O campo nome não pode ser vazio")
    protected String nome;

    @NotNull(message = "O campo RG não pode ser nulo")
    @NotBlank(message = "O campo rg não pode ser vazio")
    protected String rg;

    @NotNull(message = "O campo CPF não pode ser nulo!")
    @CPF
    protected String cpf;

    @NotNull(message = "O campo telefone não pode ser nulo")
    @NotBlank(message = "O campo telefone não pode estar vazio!")
    protected String telefone;

    @NotNull(message = "O campo email não pode ser nulo")
    @NotBlank(message = "O campo email não pode estar vazio")
    protected String email;

    @NotNull(message = "O campo senha não pode ser nulo")
    @NotBlank(message = "O campo senha não pode estar vazio")
    protected String senha;

    protected int status;

    protected Set<Integer> tipoPessoa = new HashSet<>();

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.rg = usuario.getRg();
        this.cpf = usuario.getCpf();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.status = usuario.getStatus().getId();
        this.tipoPessoa.stream().map(TipoPessoa::toEnum).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O campo nome não pode ser nulo") @NotBlank(message = "O campo nome não pode ser vazio") String getNome() {
        return nome;
    }

    public void setNome(@NotNull(message = "O campo nome não pode ser nulo") @NotBlank(message = "O campo nome não pode ser vazio") String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "O campo RG não pode ser nulo") String getRg() {
        return rg;
    }

    public void setRg(@NotNull(message = "O campo RG não pode ser nulo") String rg) {
        this.rg = rg;
    }

    public @NotNull(message = "O campo CPF não pode ser nulo!") @CPF String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull(message = "O campo CPF não pode ser nulo!") @CPF String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank(message = "O campo telefone não pode estar vazio!") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank(message = "O campo telefone não pode estar vazio!") String telefone) {
        this.telefone = telefone;
    }

    public @NotNull(message = "O campo email não pode ser nulo") @NotBlank(message = "O campo email não pode estar vazio") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "O campo email não pode ser nulo") @NotBlank(message = "O campo email não pode estar vazio") String email) {
        this.email = email;
    }

    public @NotNull(message = "O campo senha não pode ser nulo") @NotBlank(message = "O campo senha não pode estar vazio") String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull(message = "O campo senha não pode ser nulo") @NotBlank(message = "O campo senha não pode estar vazio") String senha) {
        this.senha = senha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<TipoPessoa> getTipoPessoa() {
        return tipoPessoa == null ? Collections.emptySet() :
                tipoPessoa.stream().map(TipoPessoa::toEnum)
                        .collect(Collectors.toSet());
    }

    public void addTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa.add(tipoPessoa.getId());
    }
}
