package br.com.fiap.Calendario2.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.Calendario2.exceptions.RestNotFoundException;
import br.com.fiap.Calendario2.models.Evento;
import br.com.fiap.Calendario2.repository.ContaRepository;
import br.com.fiap.Calendario2.repository.EventoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/eventos")
@Slf4j
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Evento")
public class EventoController {


    Logger log = LoggerFactory.getLogger(EventoController.class);
    
    @Autowired
    EventoRepository eventorepository;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
        Page<Evento> eventos;
        eventos = (busca == null) ?
            eventorepository.findAll(pageable): 
            eventorepository.findByNomeContaining(busca, pageable);
            
        return assembler.toModel(eventos.map(Evento::toEntityModel)); 
        

    }

    // método criado para cadastrar um evento

    @PostMapping
    @ApiResponses ({
        @ApiResponse(responseCode = "201", description = "evento cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Os campos enviados são inválidos")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Evento evento ){
        log.info("Cadastrando evento " + evento);   
        eventorepository.save(evento);
        evento.setConta(contaRepository.findById(evento.getConta().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(evento);
    }

    // método criado para detalhar um evento específico

    @GetMapping("{id}")
    @Operation(
        summary = "Detalhar evento",
        description = "Endpoint que recebe um id e retorna os dados da evento. O id deve ser ..."

    )
    public EntityModel<Evento> show(@PathVariable long id){
        log.info("detalhando evento " + id);

        var evento =  eventorepository.findById(id).orElseThrow(()-> new RestNotFoundException("evento não encontrado"));
           
        return evento.toEntityModel();

    }



    // metodo para atualizar um evento
    @PutMapping("{id}")
    @ApiResponses ({
        @ApiResponse(responseCode = "201", description = "dados atualizados com sucesso"),
        @ApiResponse(responseCode = "404", description = "não existe dado com o id informado")
    })
    public EntityModel<Evento> update(@PathVariable long id, @RequestBody @Valid Evento evento){
        log.info("atualizando evento " + id);
        getEvento(id);
        evento.setId(id);
        eventorepository.save(evento);
        return evento.toEntityModel();
    }

    @DeleteMapping("{id}")
    @ApiResponses ({
        @ApiResponse(responseCode = "203", description = "dado apagado com sucesso"),
        @ApiResponse(responseCode = "404", description = "não existe dado com o id informado")
    })
    public ResponseEntity<Evento> delete(@PathVariable long id){
        log.info("atualizando evento " + id);
        eventorepository.delete( getEvento(id));
        return ResponseEntity.noContent().build();
    }




    private Evento getEvento(long id) {
        return eventorepository.findById(id).orElseThrow( ()-> new RestNotFoundException("Evento não encontrado"));
    }




}