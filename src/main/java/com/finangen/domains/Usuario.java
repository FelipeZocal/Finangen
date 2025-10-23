package com.finangen.domains;

import com.finangen.domains.dtos.UsuarioDTO;
import com.finangen.domains.enums.Status;
import com.finangen.domains.enums.TipoPessoa;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("USUARIO")
public class Usuario extends Pessoa{

    public Usuario(Long id, String nome, String rg, String cpf, String telefone, String email, String senha, Status status) {
        super(id, nome, rg, cpf, telefone, email, senha, status);
        addTipoPessoa(TipoPessoa.USUARIO);
    }

    public Usuario(UsuarioDTO dto){

        this.id = dto.getId();
        this.nome = dto.getNome();
        this.rg = dto.getRg();
        this.cpf = dto.getCpf();
        this.telefone = dto.getTelefone();
        this.email = dto.getEmail();
        this.senha = dto.getSenha();
        this.status = Status.toEnum(dto.getStatus());
        this.tipoPessoa = dto.getTipoPessoa().stream()
                .map(x -> x.getId()).collect(Collectors.toSet());
        addTipoPessoa(TipoPessoa.USUARIO);

    }

    public Usuario(){
        super();
        addTipoPessoa(TipoPessoa.USUARIO);
    }
}
