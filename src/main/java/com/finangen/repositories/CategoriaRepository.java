package com.finangen.repositories;

import com.finangen.domains.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByIdCategoria(Long idCategoria);

}
