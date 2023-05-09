package br.com.fiap.Calendario2.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Calendario2.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    
    Page<Conta> findByUsuarioContaining(String busca, Pageable pageable);

     Optional<Conta> findByEmail(String username);

    
}
