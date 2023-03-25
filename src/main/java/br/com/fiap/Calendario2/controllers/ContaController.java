
package br.com.fiap.Calendario2.controllers;

import java.util.ArrayList;
import java.util.List;

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

import br.com.fiap.Calendario2.models.Conta;
import br.com.fiap.Calendario2.repository.ContaRepository;

@RestController
@RequestMapping("/api/conta")
public class ContaController {
    

    Logger log = LoggerFactory.getLogger(ContaController.class);

    @Autowired
    ContaRepository repository;

    @GetMapping
    public List<Conta> index(){
        return repository.findAll();
    }

    // Método para cadastrar conta 
    @PostMapping
    public ResponseEntity<Conta> create(@RequestBody Conta conta){
        log.info("Cadastrando conta" + conta);
        repository.save(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }


    // metodo para detalhar uma conta
    
    @GetMapping("{id}")
    public ResponseEntity<Conta> show(@PathVariable long id){
        log.info("detalhando conta" + id);

        var contaEncontrado = repository.findById(id);

        if(contaEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contaEncontrado.get());

    }

    // metodo para atualizar uma conta
    @PutMapping("{id}")
    public ResponseEntity<Conta> update(@PathVariable long id, @RequestBody Conta conta){
        log.info("atualizando conta" + id);

        var contaEncontrado = repository.findById(id);

        if(contaEncontrado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        conta.setId(id);
        repository.save(conta);

        return ResponseEntity.ok(conta);
    }


    // metodo para deletar uma conta
    @DeleteMapping("{id}")
    public ResponseEntity<Conta> delete(@PathVariable long id){
        log.info("deletando conta" + id);

        var contaEncontrado = repository.findById(id);

        if(contaEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repository.delete(contaEncontrado.get());   

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
