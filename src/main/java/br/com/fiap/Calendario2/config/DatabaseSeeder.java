package br.com.fiap.Calendario2.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.Calendario2.models.Conta;
import br.com.fiap.Calendario2.models.Evento;
import br.com.fiap.Calendario2.repository.ContaRepository;

@Configuration  
public class DatabaseSeeder  implements CommandLineRunner{

    @Autowired 
    ContaRepository contaRepository;

    @Override
    public void run(String... args) throws Exception {
        contaRepository.saveAll(List.of(
            new Conta(1L, "FernandoCesxr", "rm95628@fiap.com.br", "vF#zP9@qX$mT", true),
            new Conta(2L, "Fernando", "fernando@gmail.com", "nX@3ugG3I*", true),
            new Conta(3L, "Cesar", "cesar@gmail.com", "ZyCbEk@n", true)
            ));
    }
    
}
