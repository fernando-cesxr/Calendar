package br.com.fiap.Calendario2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.RepositoryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.Calendario2.models.Evento;
import br.com.fiap.Calendario2.repository.EventoRepository;

@RestController
@RequestMapping("/api/evento")
public class EventoController {


    Logger log = LoggerFactory.getLogger(EventoController.class);

    @Autowired
    EventoRepository repository;

    @GetMapping
    public List<Evento> index(){
        return repository.findAll();
    }

    // método criado para cadastrar um evento

    @PostMapping
    public ResponseEntity<Evento> create(@RequestBody Evento evento){
        log.info("Cadastrando evento" + evento);
        repository.save(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(evento);
    }

    // método criado para detalhar um evento específico

    @GetMapping("{id}")
    public ResponseEntity<Evento> show(@PathVariable long id){
        log.info("detalhando evento" + id);

        var eventoEncontrado = repository.findById(id);
        if(eventoEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(eventoEncontrado.get());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Evento> delete(@PathVariable long id){
        log.info("atualizando evento" + id);

        var eventoEncontrado = repository.findById(id);

        if(eventoEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        repository.delete(eventoEncontrado.get());

        return ResponseEntity.noContent().build();
    }


    // metodo para atualizar um evento
    @PutMapping("{id}")
    public ResponseEntity<Evento> update(@PathVariable long id, @RequestBody Evento evento){
        log.info("atualizando evento" + id);

        var eventoEncontrado = repository.findById(id);

        if(eventoEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        evento.setId(id);
        repository.save(evento);

        return ResponseEntity.ok(evento);
    }

    




}
