package br.com.fiap.Calendario2.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.Calendario2.models.Evento;

@RestController
public class EventoController {


    Logger log = LoggerFactory.getLogger(EventoController.class);

    List<Evento> eventos = new ArrayList<>();

    @GetMapping("api/evento")
    public List<Evento> index(){
        return eventos;
    }

    // método criado para cadastrar um evento

    @PostMapping("api/evento")
    public void create(@RequestBody Evento evento){
        log.info("Cadastrando evento" + evento);
        evento.setId(eventos.size() + 1l);
        eventos.add((evento));
    }

    // método criado para detalhar um evento específico

    @GetMapping("api/evento/{id}")
    public ResponseEntity<Evento> show(@PathVariable long id){
        log.info("detalhando evento" + id);

        var eventoEncontrado = eventos.stream().filter(e -> e.getId().equals(id)).findFirst();

        if(eventoEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(eventoEncontrado.get());

    }

    // metodo para atualizar um evento
    @PutMapping("api/evento/{id}")
    public ResponseEntity<Evento> update(@PathVariable long id, @RequestBody Evento evento){
        log.info("atualizando evento" + id);

        var eventoEncontrado = eventos.stream().filter(e -> e.getId().equals(id)).findFirst();

        if(eventoEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        eventos.remove(eventoEncontrado.get());
        evento.setId(id);
        eventos.add(evento);

        return ResponseEntity.ok(evento);
    }

    @DeleteMapping("api/evento/{id}")
    public ResponseEntity<Evento> delete(@PathVariable long id){
        log.info("atualizando evento" + id);

        var eventoEncontrado = eventos.stream().filter(e -> e.getId().equals(id)).findFirst();

        if(eventoEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        eventos.remove(eventoEncontrado.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    // atividade do system model 
    
    @GetMapping("/api/eventos")
    public Evento showSystemModel(){
        return new Evento("Médico", "15/03/2023", "17:30", "1 dia antes", "FernandoCesxr");
    }


}
