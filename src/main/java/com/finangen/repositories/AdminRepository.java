package com.finangen.repositories;

import com.finangen.domains.Admin;
import com.finangen.domains.Pessoa;
import com.finangen.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findById(Long id);
    Optional<Admin> findByCpf(String cpf);
    Optional<Admin> findByEmail(String email);
}

