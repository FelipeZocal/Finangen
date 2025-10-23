

package com.finangen.domains.dtos;

import com.finangen.domains.ContaBancaria;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ContaBancariaDTO {
    private Long idConta;

    @NotNull(message = "O campo descricaoConta não pode ser vazio")
    @NotBlank(message = "O campo descricaoConta não pode ser vazio")
    private String descricaoConta;

    @NotNull(message = "O campo agenciaConta não pode ser vazio")
    @NotBlank(message = "O campo agenciaConta não pode ser vazio")
    private String agenciaConta;

    @NotNull(message = "O campo numeroConta não pode ser vazio")
    @NotBlank(message = "O campo numeroConta não pode ser vazio")
    private String numeroConta;

    @NotNull(message = "O campo limiteConta não pode ser vazio")
    @NotBlank(message = "O campo limiteConta não pode ser vazio")
    private String limiteConta;

    @NotNull(message = "O campo saldoConta não pode ser vazio")
    @Digits(integer = 15, fraction = 3)
    private BigDecimal saldoConta;

    private int tipoConta;

    @NotNull(message = "Campo Pessoa é obrigatório")
    private Long id;
    private String nome;
    private String cpf;

    @NotNull(message = "Campo Banco é obrigatório")
    private Long idBanco;
    private String razaoSocial;

    public ContaBancariaDTO() {}

    public ContaBancariaDTO(ContaBancaria contaBancaria) {
        this.idConta = contaBancaria.getIdConta();
        this.descricaoConta = contaBancaria.getDescricaoConta();
        this.agenciaConta = contaBancaria.getAgenciaConta();
        this.numeroConta = contaBancaria.getNumeroConta();
        this.limiteConta = contaBancaria.getLimiteConta();
        this.saldoConta = contaBancaria.getSaldoConta();
        this.tipoConta = contaBancaria.getTipoConta().getId();

        this.id = contaBancaria.getPessoa().getId();
        this.nome = contaBancaria.getPessoa().getNome();
        this.cpf = contaBancaria.getPessoa().getCpf();

        this.idBanco = contaBancaria.getBanco().getIdBanco();
        this.razaoSocial = contaBancaria.getBanco().getRazaoSocial();
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public @NotNull(message = "O campo descricaoConta não pode ser vazio") @NotBlank(message = "O campo descricaoConta não pode ser vazio") String getDescricaoConta() {
        return descricaoConta;
    }

    public void setDescricaoConta(@NotNull(message = "O campo descricaoConta não pode ser vazio") @NotBlank(message = "O campo descricaoConta não pode ser vazio") String descricaoConta) {
        this.descricaoConta = descricaoConta;
    }

    public @NotNull(message = "O campo agenciaConta não pode ser vazio") @NotBlank(message = "O campo agenciaConta não pode ser vazio") String getAgenciaConta() {
        return agenciaConta;
    }

    public void setAgenciaConta(@NotNull(message = "O campo agenciaConta não pode ser vazio") @NotBlank(message = "O campo agenciaConta não pode ser vazio") String agenciaConta) {
        this.agenciaConta = agenciaConta;
    }

    public @NotNull(message = "O campo numeroConta não pode ser vazio") @NotBlank(message = "O campo numeroConta não pode ser vazio") String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(@NotNull(message = "O campo numeroConta não pode ser vazio") @NotBlank(message = "O campo numeroConta não pode ser vazio") String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public @NotNull(message = "O campo limiteConta não pode ser vazio") @NotBlank(message = "O campo limiteConta não pode ser vazio") String getLimiteConta() {
        return limiteConta;
    }

    public void setLimiteConta(@NotNull(message = "O campo limiteConta não pode ser vazio") @NotBlank(message = "O campo limiteConta não pode ser vazio") String limiteConta) {
        this.limiteConta = limiteConta;
    }

    public @NotNull(message = "O campo saldoConta não pode ser vazio") @Digits(integer = 15, fraction = 3) BigDecimal getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(@NotNull(message = "O campo saldoConta não pode ser vazio") @Digits(integer = 15, fraction = 3) BigDecimal saldoConta) {
        this.saldoConta = saldoConta;
    }

    public int getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(int tipoConta) {
        this.tipoConta = tipoConta;
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

    public @NotNull(message = "") Long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(@NotNull(message = "") Long idBanco) {
        this.idBanco = idBanco;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}
