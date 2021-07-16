package com.example.marcali;

public class Estabelecimento {

    String tipo, nome, username, password, morada;

    public Estabelecimento(){

    }

    public Estabelecimento(String tipo, String nome, String username, String password, String morada){
        this.tipo = tipo;
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.morada = morada;
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

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }
}
