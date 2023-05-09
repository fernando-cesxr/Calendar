package br.com.fiap.Calendario2.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.Calendario2.models.Conta;
import br.com.fiap.Calendario2.models.Evento;
import br.com.fiap.Calendario2.repository.ContaRepository;
import br.com.fiap.Calendario2.repository.EventoRepository;

@Configuration  
public class DatabaseSeeder  implements CommandLineRunner{

    @Autowired 
    ContaRepository contaRepository;


    @Autowired
    EventoRepository eventoRepository;

    @Override
    public void run(String... args) throws Exception {
        Conta c1 = new Conta(1L, "FernandoCesxr", "rm95628@fiap.com.br", "vF#zP9@qX$mT", true);
        Conta c2 = new Conta(2L, "Fernando", "fernando@gmail.com", "nX@3ugG3I*", true);
        Conta c3 = new Conta(3L, "Cesar", "cesar@gmail.com", "  ", true);
        contaRepository.saveAll(List.of(c1,c2,c3));

        eventoRepository.saveAll(List.of(
            Evento.builder().nome("médico").data(LocalDate.now()).horario(LocalTime.now()).lembrete("1 dia antes").conta(c1).build(),
            Evento.builder().nome("dentista").data(LocalDate.now()).horario(LocalTime.now()).lembrete("1 dia antes").conta(c1).build(),
            Evento.builder().nome("barbeiro").data(LocalDate.now()).horario(LocalTime.now()).lembrete("2 horas antes").conta(c1).build(),
            Evento.builder().nome("show").data(LocalDate.now()).horario(LocalTime.now()).lembrete("2 horas antes").conta(c1).build(),
            Evento.builder().nome("restaurante").data(LocalDate.now()).horario(LocalTime.now()).lembrete("5 horas antes").conta(c1).build(),
            Evento.builder().nome("sair com os amigos").data(LocalDate.now()).horario(LocalTime.now()).lembrete("2 horas antes").conta(c1).build(),
            Evento.builder().nome("médico").data(LocalDate.now()).horario(LocalTime.now()).lembrete("1 dia antes").conta(c1).build(),
            Evento.builder().nome("prova").data(LocalDate.now()).horario(LocalTime.now()).lembrete("1 dia antes").conta(c1).build(),
            Evento.builder().nome("entrega do trabalho").data(LocalDate.now()).horario(LocalTime.now()).lembrete("1 dia antes").conta(c1).build(),
            Evento.builder().nome("prova").data(LocalDate.now()).horario(LocalTime.now()).lembrete("1 dia antes").conta(c1).build()
        ));
    }
    
}