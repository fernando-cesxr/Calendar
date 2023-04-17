package br.com.fiap.Calendario2.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/evento")
@Slf4j
public class EventoController {


    Logger log = LoggerFactory.getLogger(EventoController.class);

    @Autowired
    EventoRepository eventorepository;

    @Autowired
    ContaRepository contaRepository;

    @GetMapping
    public Page<Evento> index(@RequestParam(required = false) String busca,@PageableDefault(size = 5) Pageable pageable){
        if(busca == null) return eventorepository.findAll(pageable);
        return eventorepository.findByNomeContaining(busca, pageable);
    }

    // método criado para cadastrar um evento

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Evento evento ){
        log.info("Cadastrando evento" + evento);
        eventorepository.save(evento);
        evento.setConta(contaRepository.findById(evento.getConta().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(evento);
    }

    // método criado para detalhar um evento específico

    @GetMapping("{id}")
    public ResponseEntity<Evento> show(@PathVariable long id){
        log.info("detalhando evento" + id);

        var evento = getEvento(id);

        return ResponseEntity.ok(evento);

    }



    // metodo para atualizar um evento
    @PutMapping("{id}")
    public ResponseEntity<Evento> update(@PathVariable long id, @RequestBody @Valid Evento evento){
        log.info("atualizando evento" + id);
        getEvento(id);
        evento.setId(id);
        eventorepository.save(evento);
        return ResponseEntity.ok(evento);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Evento> delete(@PathVariable long id){
        log.info("atualizando evento" + id);
        eventorepository.delete( getEvento(id));
        return ResponseEntity.noContent().build();
    }




    private Evento getEvento(long id) {
        return eventorepository.findById(id).orElseThrow( ()-> new RestNotFoundException("Evento não encontrado"));
    }




}