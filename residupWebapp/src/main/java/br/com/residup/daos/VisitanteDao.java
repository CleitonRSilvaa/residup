package br.com.residup.daos;
import br.com.residup.models.Visitante;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.com.residup.shared.GerenciadorConexaoH2.abrirConexao;

public  class VisitanteDao {
	private static VisitanteDao instance;
	private Connection connection;
	public VisitanteDao() {
		this.handleOpenConnection();
	}
	private void handleOpenConnection() {
		try {
			this.connection = abrirConexao();

		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MoradorDao.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(MoradorDao.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static VisitanteDao getInstance() {
		if(instance == null) {
			instance = new VisitanteDao();
		}
		return instance;
	}

	public static boolean inserirVisitante(Visitante visitante) {
		String create = "INSERT INTO VISITANTE (NOME, SOBRENOME, DOCUMENTO, TELEFONE)\n" +
				"VALUES (?,?,?,?);";
		try {
			Connection connection = abrirConexao();
			PreparedStatement pst = connection.prepareStatement(create);
			pst.setString(1, visitante.getNome());
			pst.setString(2, visitante.getSobrenome());
			pst.setString(3, visitante.getDocumento());
			pst.setString(4, visitante.getFone());
			pst.executeUpdate();
			connection.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static ArrayList<Visitante> listarVisitantes() {
		ArrayList<Visitante> visitantes = new ArrayList<>();
		String read = "select * from visitante ";
		try {
			Connection connection = abrirConexao();
			PreparedStatement pst = connection.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String sobreNome = rs.getString(3);
				String documento = rs.getString(4);
				String fone = rs.getString(5);
				var v = new Visitante(nome,sobreNome,documento);
				v.setId(id);
				v.setFone(fone);
				visitantes.add(v);
			}
			return visitantes;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static void selecionarVisitante(Visitante visitante) {
		String read2 = "select * from visitante where ID_VISITANTE  = ?";
		try {
			Connection connection = abrirConexao();
			PreparedStatement pst = connection.prepareStatement(read2);
			pst.setString(1, visitante.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String sobreNome = rs.getString(3);
				String documento = rs.getString(4);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static Boolean alterarVisitante(Visitante visitante) {
		String update = "update visitante set nome=?,sobrenome=?,documento=?, telefone =?  where ID_VISITANTE =?";
		try {
			Connection connection = abrirConexao();
			PreparedStatement pst = connection.prepareStatement(update);
			pst.setString(1, visitante.getNome());
			pst.setString(2, visitante.getSobrenome());
			pst.setString(3, visitante.getDocumento());
			pst.setString(4, visitante.getFone());
			pst.setString(5, visitante.getId());
			pst.executeUpdate();
			connection.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static boolean deletarVisitante(Visitante visitante) {
		String delete = "delete from visitante where ID_VISITANTE=?";
		try {
			Connection connection = abrirConexao();
			PreparedStatement pst = connection.prepareStatement(delete);
			pst.setString(1, visitante.getId());
			pst.executeUpdate();
			connection.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
