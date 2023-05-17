package br.com.residup.models;

public class OcorrenciaBuilder {
    private int id;
    private String titulo;
    private String texto;
    private String status;

    public OcorrenciaBuilder id(int id) {
        this.id = id;
        return this;
    }

    public OcorrenciaBuilder titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public OcorrenciaBuilder texto(String texto) {
        this.texto = texto;
        return this;
    }

    public OcorrenciaBuilder status(String status) {
        this.status = status;
        return this;
    }

    public Ocorrencia build(){
        return new Ocorrencia(titulo, texto, status, id);
    }
}
