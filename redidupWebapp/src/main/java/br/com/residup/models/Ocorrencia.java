package br.com.residup.models;

public class Ocorrencia {
    private int id;
    private String titulo;
    private String texto;
    private String resolucao;

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Ocorrencia(String titulo, String texto, String resolucao) {
        this.titulo = titulo;
        this.texto = texto;
        this.resolucao = resolucao;
    }
}
