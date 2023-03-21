package br.com.fiap.Calendario2.models;

public class Conta {
    private Long id;
    private String user, email, senha;

    public Conta(String user, String email, String senha) {
        this.user = user;
        this.email = email;
        this.senha = senha;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    @Override
    public String toString() {
        return "Conta [user=" + user + ", email=" + email + ", senha=" + senha + "]";
    }

    



}
