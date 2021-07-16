package com.example.marcali;

public class Comentario {

    String user, estabelecimento, texto;
    float rating;

    public Comentario() {

    }

    public Comentario(String user, String estabelecimento, String texto, float rating) {
        this.user = user;
        this.estabelecimento = estabelecimento;
        this.texto = texto;
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
