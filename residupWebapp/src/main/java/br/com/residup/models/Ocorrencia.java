package br.com.residup.models;

public class Ocorrencia {
    private int id;
    private String titulo;
    private String texto;
    private String resolucao;

    public int getId_morador() {
        return id_morador;
    }

    public void setId_morador(int id_morador) {
        this.id_morador = id_morador;
    }

    private int id_morador;

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

    public Ocorrencia(String titulo, String texto, String resolucao, int id_morador) {
        this.titulo = titulo;
        this.texto = texto;
        this.resolucao = resolucao;
        this.id_morador = id_morador;
    }

    public Ocorrencia() {}

    @Override
    public String toString() {
        return "Ocorrencia{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", resolucao='" + resolucao + '\'' +
                ", id_morador=" + id_morador +
                '}';
    }
}
