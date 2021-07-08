package com.example.marcali;

public class Servico {

    String nome, username, preco;

    public Servico() {

    }

    public Servico(String nome, String username, String preco) {
        this.nome = nome;
        this.username = username;
        this.preco = preco;
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

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
