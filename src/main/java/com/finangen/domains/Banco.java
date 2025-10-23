package com.finangen.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finangen.domains.dtos.BancoDTO;
import com.finangen.domains.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "banco")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_banco")
    private Long idBanco;

    @NotNull
    @NotBlank
    private String razaoSocial;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "status")
    private Status status;

    @JsonIgnore
    @OneToMany(mappedBy = "banco")
    private List<ContaBancaria> contaBancarias = new ArrayList<>();

    public Banco() {}

    public Banco(Long idBanco, String razaoSocial, Status status) {
        this.idBanco = idBanco;
        this.razaoSocial = razaoSocial;
        this.status = status;
    }

    public Banco(BancoDTO dto) {
        this.idBanco = dto.getIdBanco();
        this.razaoSocial = dto.getRazaoSocial();
        this.status = Status.toEnum(dto.getStatus());
    }

    public Long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Long idBanco) {
        this.idBanco = idBanco;
    }

    public @NotNull @NotBlank String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(@NotNull @NotBlank String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<ContaBancaria> getContaBancarias() {
        return contaBancarias;
    }

    public void setContaBancarias(List<ContaBancaria> contaBancarias) {
        this.contaBancarias = contaBancarias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banco banco = (Banco) o;
        return Objects.equals(idBanco, banco.idBanco) && Objects.equals(razaoSocial, banco.razaoSocial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBanco, razaoSocial);
    }
}
