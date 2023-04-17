package br.com.fiap.Calendario2.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.Calendario2.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    // @Query("SELECT e FROM Evento e WHERE e.nome LIKE %?1%")    
    Page<Evento> findByNomeContaining(String busca, Pageable pageable);
    // List<Evento> findByConta(String conta);
    // List<Evento> findByContaAndNome(String conta, String nome);
    // List<Evento> findByValorGratherThan(BigDecimal valor);

    // @Query("SELECT e FROM Evento e ORDER BY e.id LIMIT ?1 OFFSET ?2")
    // List<Evento> buscarPaginado(int tamanho, int offset);
    
}
