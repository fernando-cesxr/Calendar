
package br.com.fiap.Calendario2.controllers;

import java.util.ArrayList;
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
import br.com.fiap.Calendario2.models.Conta;
import br.com.fiap.Calendario2.models.RestValidationError;  
import br.com.fiap.Calendario2.repository.ContaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/conta")
@Slf4j
public class ContaController {
    

    Logger log = LoggerFactory.getLogger(ContaController.class);

    @Autowired
    ContaRepository contaRepository;

    @GetMapping
    public Page<Conta> index(@RequestParam(required = false) String busca,@PageableDefault(size = 5) Pageable pageable){
        if(busca == null) return contaRepository.findAll(pageable);
        return contaRepository.findByUsuarioContaining(busca, pageable);
    }

    // Método para cadastrar conta 
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Conta conta){
        log.info("Cadastrando conta" + conta);
        contaRepository.save(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);   
    }

    // metodo para detalhar uma conta
    @GetMapping("{id}")
    public ResponseEntity<Conta> show(@PathVariable long id){
        log.info("detalhando conta" + id);
        return ResponseEntity.ok(getConta(id));
    }

    // metodo para atualizar uma conta
    @PutMapping("{id}")
    public ResponseEntity<Conta> update(@PathVariable long id, @RequestBody @Valid Conta conta){
        log.info("atualizando conta" + id);
        getConta(id);
        conta.setId(id);
        contaRepository.save(conta);

        return ResponseEntity.ok(conta);
    }


    // metodo para deletar uma conta
    @DeleteMapping("{id}")
    public ResponseEntity<Conta> delete(@PathVariable long id){
        log.info("deletando conta" + id);
        var conta = getConta(id);    
        conta.setAtiva(false);
        contaRepository.save(conta);   
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    private Conta getConta(long id) {
        return contaRepository.findById(id).orElseThrow( ()-> new RestNotFoundException("Conta não encontrada"));
    }




}
