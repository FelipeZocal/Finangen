package com.finangen.repositories;

import com.finangen.domains.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BancoRepository extends JpaRepository<Banco,Long> {
    Optional<Banco> findByIdBanco(Long idBanco);
    Optional<Banco> findByRazaoSocial(String razaoSocial);
}

