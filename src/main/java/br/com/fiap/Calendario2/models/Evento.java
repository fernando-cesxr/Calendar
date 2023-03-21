package br.com.fiap.Calendario2.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Evento {
    private Long id;
    private String nome;
    private String data;
    private String horario;
    private String lembrete;
    private String user;

    public Evento(String nome, String data, String horario, String lembrete, String user) {
        this.nome = nome;
        this.data = data;
        this.horario = horario;
        this.lembrete = lembrete;
        this.user = user;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
    public String getLembrete() {
        return lembrete;
    }
    public void setLembrete(String lembrete) {
        this.lembrete = lembrete;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Evento [nome=" + nome + ", data=" + data + ", horario=" + horario + ", lembrete=" + lembrete + ", user="
                + user + "]";
    }






}
