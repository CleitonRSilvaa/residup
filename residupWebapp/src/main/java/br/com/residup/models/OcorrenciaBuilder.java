package br.com.residup.models;

public class OcorrenciaBuilder {
    private int id_ocorrencia;
    private String titulo;
    private String texto;
    private String status;
    private int id_morador;

    public OcorrenciaBuilder id_ocorrencia(int id_ocorrencia) {
        this.id_ocorrencia = id_ocorrencia;
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
    public OcorrenciaBuilder id_morador(int id_morador) {
        this.id_morador = id_morador;
        return this;
    }


    public Ocorrencia build(){
        return new Ocorrencia(titulo, texto, status,id_morador, id_ocorrencia);
    }

}
