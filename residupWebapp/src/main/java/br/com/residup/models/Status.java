package br.com.residup.models;

public enum Status {
    EM_ABERTO("Em aberto"),
    EM_ANALISE("Em análise"),
    EM_ANDAMENTO("Em andamento"),
    RESOLVIDO("Resolvido");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
