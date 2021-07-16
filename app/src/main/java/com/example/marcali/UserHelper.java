package com.example.marcali;

public class UserHelper {

    String tipo, nome, username, password, telefone;

    public UserHelper() {

    }

    public UserHelper(String tipo, String nome, String username, String password, String telefone) {
        this.tipo = tipo;
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.telefone = telefone;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
