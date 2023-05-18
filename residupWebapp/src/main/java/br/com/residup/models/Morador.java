package br.com.residup.models;

public class Morador {
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

    private String enderecoFoto;


    public Morador(String nome, String sobrenome, String cpf, String rg, String numeroApartamento, String bloco, String senhaDeAcesso) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.rg = rg;
        this.numeroApartamento = numeroApartamento;
        this.bloco = bloco;
        this.senhaDeAcesso = senhaDeAcesso;
    }

    public Morador(String cpf, String senhaDeAcesso) {
        this.cpf = cpf;
        this.senhaDeAcesso = senhaDeAcesso;
    }

//    public static String[] separarNomeSobrenome(String nomeCompleto) {
//        String[] partesNome = nomeCompleto.trim().split("\\s+");
//
//        if (partesNome.length < 2) {
//            throw new IllegalArgumentException("Nome incompleto, deve ter pelo menos nome e sobrenome.");
//        }
//
//        String nome = partesNome[0];
//        String sobrenome = partesNome[partesNome.length - 1];
//
//        for (int i = 1; i < partesNome.length - 1; i++) {
//            nome += " " + partesNome[i];
//        }
//
//        return new String[]{nome, sobrenome};
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setNumeroApartamento(String numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getSenhaDeAcesso() {
        return senhaDeAcesso;
    }

    public void setSenhaDeAcesso(String senhaDeAcesso) {
        this.senhaDeAcesso = senhaDeAcesso;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnderecoFoto() {
        return enderecoFoto;
    }

    public void setEnderecoFoto(String enderecoFoto) {
        this.enderecoFoto = enderecoFoto;
    }


    @Override
    public String toString() {
        return "Morador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", numeroApartamento='" + numeroApartamento + '\'' +
                ", bloco='" + bloco + '\'' +
                ", senhaDeAcesso='" + senhaDeAcesso + '\'' +
                ", enderecoFoto='" + enderecoFoto + '\'' +
                '}';
    }
}
