package br.com.residup.models;

public class Visitante {

	private String id;
	private String nome;
	private String  sobrenome;
	private String documento;
	private String fone;



	public Visitante() {
		super();
	}

	public Visitante(String nome, String sobrenome, String documento) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public static String[] separarNomeSobrenome(String nomeCompleto) {
		String[] partesNome = nomeCompleto.trim().split("\\s+");

		if (partesNome.length < 2) {
			throw new IllegalArgumentException("Nome incompleto, deve ter pelo menos nome e sobrenome.");
		}

		String nome = partesNome[0];
		String sobrenome = partesNome[partesNome.length - 1];

		for (int i = 1; i < partesNome.length - 1; i++) {
			nome += " " + partesNome[i];
		}

		return new String[]{nome, sobrenome};
	}

	@Override
	public String toString() {
		return "Visitante{" +
				"id='" + id + '\'' +
				", nome='" + nome + '\'' +
				", documento='" + documento + '\'' +
				'}';
	}
}
