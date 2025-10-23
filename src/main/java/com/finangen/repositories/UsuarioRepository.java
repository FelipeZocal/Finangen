package com.finangen.repositories;

import com.finangen.domains.Pessoa;
import com.finangen.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByCpf(String cpf);
    Optional<Usuario> findByEmail(String email);
}
