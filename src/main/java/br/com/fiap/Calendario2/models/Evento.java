package br.com.fiap.Calendario2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String data;
    private String horario;
    private String lembrete;
    private String usuario;

    protected Evento(){}

    public Evento(String nome, String data, String horario, String lembrete, String usuario) {
        this.nome = nome;
        this.data = data;
        this.horario = horario;
        this.lembrete = lembrete;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    @Override
    public String toString() {
        return "Evento [nome=" + nome + ", data=" + data + ", horario=" + horario + ", lembrete=" + lembrete + ", usuario="
                + usuario + "]";
    }






}
