package com.example.marcali;

public class UserHelper {

    String tipo, nome, username, email, password;

    public UserHelper() {

    }

    public UserHelper(String tipo, String nome, String username, String email, String password) {
        this.tipo = tipo;
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
