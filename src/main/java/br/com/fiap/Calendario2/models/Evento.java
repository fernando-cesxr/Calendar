package br.com.fiap.Calendario2.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import br.com.fiap.Calendario2.controllers.ContaController;
import br.com.fiap.Calendario2.controllers.EventoController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Size(min = 3, max = 255)
    private String nome;

    @NotNull 
    private LocalDate data;

    // @Size(min = 5, max = 255)
    @NotNull 
    private LocalTime horario;

    private String lembrete;    

    @ManyToOne
    private Conta conta;

    public EntityModel<Evento> toEntityModel(){
        
        return EntityModel.of(
            this,
            linkTo(methodOn(EventoController.class).show(id)).withSelfRel(), 
            linkTo(methodOn(EventoController.class).delete(id)).withRel("delete"), 
            linkTo(methodOn(EventoController.class).index(null, Pageable.unpaged())).withRel("all"), 
            linkTo(methodOn(ContaController.class).show(this.getConta().getId())).withRel("conta")         
        );
    }

}
