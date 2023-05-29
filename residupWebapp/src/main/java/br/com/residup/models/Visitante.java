package br.com.residup.models;

public class Visitante {

	private String id;
	private String nome;
	private String  sobrenome;
	private String documento;
	private String fone;
	private String checkIn  ;
	private String idRegistro;

	private  Morador morador;


	public Visitante() {
		super();
	}

	public Visitante(String nome, String sobrenome, String documento) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.checkIn = null;
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

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	public static String[] separarNomeSobrenome(String nomeCompleto) {
		String[] partesNome = nomeCompleto.trim().split(" ");

		if (partesNome.length < 2) {
			throw new IllegalArgumentException("Nome incompleto, deve ter pelo menos nome e sobrenome.");
		}

		String nome = partesNome[0];
		String sobrenome = "";
//		=partesNome[0] +"" partesNome[partesNome.length - 1];

		for (int i = 1; i < partesNome.length ; i++) {
			sobrenome +=" " + partesNome[i];
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
