package com.finangen.repositories;

import com.finangen.domains.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
   /* Optional<Lancamento> findByIdLancamento(Long idLancamento);*/
}