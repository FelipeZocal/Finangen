package com.finangen.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finangen.domains.dtos.LancamentoDTO;
import com.finangen.domains.enums.Situacao;
import com.finangen.domains.enums.TipoLancamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "lancamento")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lancamento")
    private Long idLancamento;

    @NotNull @NotBlank
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataBaixa = LocalDate.now();

    @NotNull
    @Digits(integer=15, fraction=2)
    private BigDecimal valorLancamento;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "tipoLancamento")
    private TipoLancamento tipoLancamento;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "situacao")
    private Situacao situacao;

    @ManyToOne
    @JoinColumn(name = "idpessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idconta")
    private ContaBancaria contaBancaria;


    public Lancamento() {}

    public Lancamento(Long idLancamento, String descricao, LocalDate dataLancamento, LocalDate dataVencimento, LocalDate dataBaixa, BigDecimal valorLancamento, TipoLancamento tipoLancamento, Situacao situacao, Pessoa pessoa, Categoria categoria, ContaBancaria contaBancaria) {
        this.idLancamento = idLancamento;
        this.descricao = descricao;
        this.dataLancamento = dataLancamento;
        this.dataVencimento = dataVencimento;
        this.dataBaixa = dataBaixa;
        this.valorLancamento = valorLancamento;
        this.tipoLancamento = tipoLancamento;
        this.situacao = situacao;
        this.pessoa = pessoa;
        this.categoria = categoria;
        this.contaBancaria = contaBancaria;
    }

    public Lancamento(LancamentoDTO dto) {
        this.idLancamento = dto.getIdLancamento();
        this.descricao = dto.getDescricao();
        this.dataLancamento = dto.getDataLancamento();
        this.dataVencimento = dto.getDataVencimento();
        this.dataBaixa = dto.getDataBaixa();
        this.valorLancamento = dto.getValorLancamento();
        this.tipoLancamento = TipoLancamento.toEnum(dto.getTipoLancamento());
        this.situacao = Situacao.toEnum(dto.getSituacao());

        this.pessoa = new Usuario();
        this.pessoa.setId(dto.getId());

        this.categoria = new Categoria();
        this.categoria.setIdCategoria(dto.getIdCategoria());

        this.contaBancaria = new ContaBancaria();
        this.contaBancaria.setIdConta(dto.getIdConta());

    }


    public Long getIdLancamento() {
        return idLancamento;
    }

    public void setIdLancamento(Long idLancamento) {
        this.idLancamento = idLancamento;
    }

    public @NotNull @NotBlank String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull @NotBlank String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(LocalDate dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public @NotNull @Digits(integer = 15, fraction = 2) BigDecimal getValorLancamento() {
        return valorLancamento;
    }

    public void setValorLancamento(@NotNull @Digits(integer = 15, fraction = 2) BigDecimal valorLancamento) {
        this.valorLancamento = valorLancamento;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Lancamento that = (Lancamento) o;
        return Objects.equals(idLancamento, that.idLancamento);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idLancamento);
    }
}
