package com.finangen.domains.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finangen.domains.Lancamento;
import com.finangen.domains.enums.Situacao;
import com.finangen.domains.enums.TipoLancamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoDTO {

    private Long idLancamento;

    @NotNull(message = "O campo descricao não pode ser nulo!")
    @NotBlank(message = "O campo descricao não pode ser vazio!")
    private String descricao;

    @NotNull(message = "O campo dataLancamento não pode ser nulo!")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento = LocalDate.now();

    @NotNull(message = "O campo dataVencimento não pode ser nulo!")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento = LocalDate.now();

    @NotNull(message = "O campo dataBaixa não pode ser nulo!")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataBaixa = LocalDate.now();

    @NotNull(message = "O campo valorLancamento não pode ser nulo!")
    @Digits(integer=15, fraction=2)
    private BigDecimal valorLancamento;

    private int tipoLancamento;
    private int situacao;

    @NotNull(message = " Campo Pessoa é obrigatório")
    private Long id;
    private String nome;
    private String cpf;

    @NotNull(message = "Campo Categoria é obrigatório")
    private Long idCategoria;
    private String descricaoCategoria;

    @NotNull(message = "Campo Conta é obrigatório")
    private Long idConta;
    private String descricaoConta;
    private String numeroConta;
    private String agenciaConta;

    public LancamentoDTO() {}

    public LancamentoDTO(Lancamento lancamento) {
        this.idLancamento = lancamento.getIdLancamento();
        this.descricao = lancamento.getDescricao();
        this.dataLancamento = lancamento.getDataLancamento();
        this.dataVencimento = lancamento.getDataVencimento();
        this.dataBaixa = lancamento.getDataBaixa();
        this.valorLancamento = lancamento.getValorLancamento();
        this.tipoLancamento = lancamento.getTipoLancamento().getId();
        this.situacao = lancamento.getSituacao().getId();

        this.id = lancamento.getPessoa().getId();
        this.nome = lancamento.getPessoa().getNome();
        this.cpf = lancamento.getPessoa().getCpf();

        this.idCategoria = lancamento.getCategoria().getIdCategoria();
        this.descricaoCategoria = lancamento.getCategoria().getDescricaoCategoria();

        this.idConta = lancamento.getContaBancaria().getIdConta();
        this.descricaoConta = lancamento.getContaBancaria().getDescricaoConta();
        this.numeroConta = lancamento.getContaBancaria().getNumeroConta();
        this.agenciaConta = lancamento.getContaBancaria().getAgenciaConta();
    }

    public Long getIdLancamento() {
        return idLancamento;
    }

    public void setIdLancamento(Long idLancamento) {
        this.idLancamento = idLancamento;
    }

    public @NotNull(message = "O campo descricao não pode ser nulo!") @NotBlank(message = "O campo descricao não pode ser vazio!") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull(message = "O campo descricao não pode ser nulo!") @NotBlank(message = "O campo descricao não pode ser vazio!") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "O campo dataLancamento não pode ser nulo!") LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(@NotNull(message = "O campo dataLancamento não pode ser nulo!") LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public @NotNull(message = "O campo dataVencimento não pode ser nulo!") LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(@NotNull(message = "O campo dataVencimento não pode ser nulo!") LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public @NotNull(message = "O campo dataBaixa não pode ser nulo!") LocalDate getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(@NotNull(message = "O campo dataBaixa não pode ser nulo!") LocalDate dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public @NotNull(message = "O campo valorLancamento não pode ser nulo!") @Digits(integer = 15, fraction = 2) BigDecimal getValorLancamento() {
        return valorLancamento;
    }

    public void setValorLancamento(@NotNull(message = "O campo valorLancamento não pode ser nulo!") @Digits(integer = 15, fraction = 2) BigDecimal valorLancamento) {
        this.valorLancamento = valorLancamento;
    }

    public int getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(int tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
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

    public @NotNull(message = "") Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(@NotNull(message = "") Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    public @NotNull(message = "") Long getIdConta() {
        return idConta;
    }

    public void setIdConta(@NotNull(message = "") Long idConta) {
        this.idConta = idConta;
    }

    public String getDescricaoConta() {
        return descricaoConta;
    }

    public void setDescricaoConta(String descricaoConta) {
        this.descricaoConta = descricaoConta;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgenciaConta() {
        return agenciaConta;
    }

    public void setAgenciaConta(String agenciaConta) {
        this.agenciaConta = agenciaConta;
    }
}
