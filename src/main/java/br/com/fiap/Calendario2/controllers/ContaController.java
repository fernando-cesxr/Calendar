
package br.com.fiap.Calendario2.controllers;

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

import br.com.fiap.Calendario2.models.Conta;

@RestController
public class ContaController {
    

    Logger log = LoggerFactory.getLogger(ContaController.class);

    List<Conta> contas = new ArrayList<>();

    @GetMapping("api/conta")
    public List<Conta> index(){
        return contas;
    }

    // Método para cadastrar conta 
    @PostMapping("api/conta")
    public void create(@RequestBody Conta conta){
        log.info("Cadastrando conta" + conta);
        conta.setId(contas.size() + 1l);
        contas.add((conta));
    }


    
    @GetMapping("api/conta/{id}")
    public ResponseEntity<Conta> show(@PathVariable long id){
        log.info("detalhando conta" + id);

        var contaEncontrado = contas.stream().filter(e -> e.getId().equals(id)).findFirst();

        if(contaEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(contaEncontrado.get());

    }

    // metodo para atualizar uma conta
    @PutMapping("api/conta/{id}")
    public ResponseEntity<Conta> update(@PathVariable long id, @RequestBody Conta conta){
        log.info("atualizando conta" + id);

        var contaEncontrado = contas.stream().filter(e -> e.getId().equals(id)).findFirst();

        if(contaEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        contas.remove(contaEncontrado.get());
        conta.setId(id);
        contas.add(conta);

        return ResponseEntity.ok(conta);
    }


    // metodo para atualizar uma conta
    @DeleteMapping("api/conta/{id}")
    public ResponseEntity<Conta> update(@PathVariable long id){
        log.info("atualizando conta" + id);

        var contaEncontrado = contas.stream().filter(e -> e.getId().equals(id)).findFirst();

        if(contaEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        contas.remove(contaEncontrado.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
