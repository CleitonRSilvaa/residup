package br.com.residup.models;

public final class Convidado {
    int id;
    String nome;
    String indentidade;
    int id_morador ;
    int id_reserva;

    public Convidado(int id, String nome, String indentidade, int id_morador, int id_reserva) {
        this.id = id;
        this.nome = nome;
        this.indentidade = indentidade;
        this.id_morador = id_morador;
        this.id_reserva = id_reserva;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIndentidade() {
        return indentidade;
    }

    public int getId_morador() {
        return id_morador;
    }

    public int getId_reserva() {
        return id_reserva;
    }
}
