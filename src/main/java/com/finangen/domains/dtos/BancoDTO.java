package com.finangen.domains.dtos;

import com.finangen.domains.Banco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BancoDTO {

    private Long idBanco;

    @NotNull(message = "O campo razaoSocial não pode ser nulo")
    @NotBlank(message = "O campo razaoSocial não pode ser vazio")
    private String razaoSocial;

    private int status;

    public BancoDTO(){}

    public BancoDTO(Banco banco) {
        this.idBanco = banco.getIdBanco();
        this.razaoSocial = banco.getRazaoSocial();
        this.status = banco.getStatus().getId();
    }

    public Long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Long idBanco) {
        this.idBanco = idBanco;
    }

    public @NotNull(message = "O campo razaoSocial não pode ser nulo") @NotBlank(message = "O campo razaoSocial não pode ser vazio") String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(@NotNull(message = "O campo razaoSocial não pode ser nulo") @NotBlank(message = "O campo razaoSocial não pode ser vazio") String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
