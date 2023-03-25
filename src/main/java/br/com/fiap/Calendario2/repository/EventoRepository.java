package br.com.fiap.Calendario2.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Calendario2.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    // List<Evento> findByConta(String conta);
    // List<Evento> findByContaAndNome(String conta, String nome);
    // List<Evento> findByValorGratherThan(BigDecimal valor);
    
}
