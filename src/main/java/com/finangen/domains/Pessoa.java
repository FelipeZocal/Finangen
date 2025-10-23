package com.finangen.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finangen.domains.enums.Status;
import com.finangen.domains.enums.TipoPessoa;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    @SequenceGenerator(name="seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1)
    protected Long id;
    protected String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataNascimento = LocalDate.now();

    @Column(unique = true)
    protected String rg;

    @Column(unique = true)
    protected String cpf;

    protected String telefone;

    @Column(unique = true)
    protected String email;
    protected String senha;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "status")
    protected Status status;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis", joinColumns = @JoinColumn(name = "pessoa_id"))
    @Column(name = "tipo_Pessoa")
    protected Set<Integer> tipoPessoa = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa")
    private List<Lancamento> lancamentos= new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa")
    private List<Categoria> categorias = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa")
    private List<ContaBancaria> contaBancarias = new ArrayList<>();

    public Pessoa() { addTipoPessoa(TipoPessoa.USUARIO);}

    public Pessoa(Long id, String nome, String rg, String cpf, String telefone, String email, String senha, Status status) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<TipoPessoa> getTipoPessoa() {
        return tipoPessoa.stream().map(TipoPessoa::toEnum).collect(Collectors.toSet());
    }

    public void setTipoPessoa(Set<Integer> tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<ContaBancaria> getContaBancarias() {
        return contaBancarias;
    }

    public void setContaBancarias(List<ContaBancaria> contaBancarias) {
        this.contaBancarias = contaBancarias;
    }

    public void addTipoPessoa(TipoPessoa tipoPessoa){ this.tipoPessoa.add(tipoPessoa.getId()); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(cpf, pessoa.cpf) && Objects.equals(email, pessoa.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, email);
    }
}
