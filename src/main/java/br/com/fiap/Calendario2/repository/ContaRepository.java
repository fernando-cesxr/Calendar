package br.com.fiap.Calendario2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Calendario2.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    
}
