package br.com.residup.models;

public class Perfil {

    private int id ;
    private String nome;
    private String  sobrenome;
    private String  cpf;
    private String  rg;
    private String  telefone;
    private String email;
    private String  numeroApartamento;
    private String  bloco;
    private String  senhaDeAcesso;

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumeroApartamento(String numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public void setSenhaDeAcesso(String senhaDeAcesso) {
        this.senhaDeAcesso = senhaDeAcesso;
    }

    public String getSenhaDeAcesso() {
        return senhaDeAcesso;
    }

    public String getBloco() {
        return bloco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getNumeroApartamento() {
        return numeroApartamento;
    }
}
