
package br.com.fiap.Calendario2.controllers;

import java.util.ArrayList;
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
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import br.com.fiap.Calendario2.models.Credencial;
import br.com.fiap.Calendario2.models.RestValidationError;
import br.com.fiap.Calendario2.repository.ContaRepository;
import br.com.fiap.Calendario2.service.TokenJwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ContaController {

    Logger log = LoggerFactory.getLogger(ContaController.class);

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenJwtService tokenJwtService;

    @GetMapping("/api/contas")
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca,
            @ParameterObject @PageableDefault(size = 5) Pageable pageable) {

        Page<Conta> contas;
        contas = (busca == null) ? contaRepository.findAll(pageable)
                : contaRepository.findByUsuarioContaining(busca, pageable);

        return assembler.toModel(contas.map(Conta::toEntityModel));
    }

    // Método para cadastrar conta
    @PostMapping("/api/registrar")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "conta cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Os campos enviados são inválidos")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Conta conta) {
        conta.setSenha(encoder.encode(conta.getSenha()));
        contaRepository.save(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    // metodo para detalhar uma conta
    @GetMapping("/api/contas/{id}")
    @Operation(summary = "Detalhar evento", description = "Endpoint que recebe um id e retorna os dados da evento. O id deve ser ..."

    )
    public ResponseEntity<Conta> show(@PathVariable long id) {
        log.info("detalhando conta" + id);
        return ResponseEntity.ok(getConta(id));
    }

    // metodo para atualizar uma conta
    @PutMapping("/api/contas/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "dados atualizados com sucesso"),
            @ApiResponse(responseCode = "404", description = "não existe dado com o id informado")
    })
    public ResponseEntity<Conta> update(@PathVariable long id, @RequestBody @Valid Conta conta) {
        log.info("atualizando conta" + id);
        getConta(id);
        conta.setId(id);
        contaRepository.save(conta);

        return ResponseEntity.ok(conta);
    }

    @DeleteMapping("/api/contas/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "203", description = "dado apagado com sucesso"),
            @ApiResponse(responseCode = "404", description = "não existe dado com o id informado")
    })
    public ResponseEntity<Conta> delete(@PathVariable long id) {
        log.info("deletando conta" + id);
        getConta(id);
        contaRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial) {
        manager.authenticate(credencial.toAuthentication());
        var token = tokenJwtService.generateToken(credencial);
        return ResponseEntity.ok(token);
    }

    private Conta getConta(long id) {
        return contaRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Conta não encontrada"));
    }

}
