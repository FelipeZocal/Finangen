package com.finangen.domains.dtos;

import com.finangen.domains.Admin;
import com.finangen.domains.enums.TipoPessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AdminDTO {

    protected Long id;

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank(message = "O campo nome não pode ser vazio")
    protected String nome;

    @NotNull(message = "O campo rg não pode ser nulo")
    @NotBlank(message = "O campo rg não pode ser vazio")
    protected String rg;

    @NotNull(message="O campo CPF não pode ser nulo!")
    @CPF
    protected String cpf;

    @NotNull(message = "O campo telefone não pode ser nulo")
    @NotBlank(message = "O campo telefone não pode ser vazio")
    protected String telefone;

    @NotNull(message = "O campo email não pode ser nulo")
    @NotBlank(message = "O campo email não pode ser vazio")
    protected String email;

    @NotNull(message = "O campo senha não pode ser nulo")
    @NotBlank(message = "O campo senha não pode ser vazio")
    protected String senha;

    protected Integer status;

    protected Set<Integer> tipoPessoa = new HashSet<>();

    public AdminDTO() { }

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.nome = admin.getNome();
        this.rg = admin.getRg();
        this.cpf = admin.getCpf();
        this.telefone = admin.getTelefone();
        this.email = admin.getEmail();
        this.senha = admin.getSenha();
        this.status = admin.getStatus().getId();
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

    public @NotNull(message = "O campo rg não pode ser nulo") @NotBlank(message = "O campo rg não pode ser vazio") String getRg() {
        return rg;
    }

    public void setRg(@NotNull(message = "O campo rg não pode ser nulo") @NotBlank(message = "O campo rg não pode ser vazio") String rg) {
        this.rg = rg;
    }

    public @NotNull(message = "O campo CPF não pode ser nulo!") @CPF String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull(message = "O campo CPF não pode ser nulo!") @CPF String cpf) {
        this.cpf = cpf;
    }

    public @NotNull(message = "O campo telefone não pode ser vazio") @NotBlank(message = "O campo telefone não pode ser vazio") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotNull(message = "O campo telefone não pode ser vazio") @NotBlank(message = "O campo telefone não pode ser vazio") String telefone) {
        this.telefone = telefone;
    }

    public @NotNull(message = "O campo email não pode ser vazio") @NotBlank(message = "O campo email não pode ser vazio") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "O campo email não pode ser vazio") @NotBlank(message = "O campo email não pode ser vazio") String email) {
        this.email = email;
    }

    public @NotNull(message = "O campo senha não pode ser vazio") @NotBlank(message = "O campo senha não pode ser vazio") String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull(message = "O campo senha não pode ser vazio") @NotBlank(message = "O campo senha não pode ser vazio") String senha) {
        this.senha = senha;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
