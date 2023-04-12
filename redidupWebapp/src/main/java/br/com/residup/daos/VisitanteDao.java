package br.com.residup.daos;
import br.com.residup.models.Visitante;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.com.residup.shared.GerenciadorConexaoH2.abrirConexao;
import static br.com.residup.shared.GerenciadorConexaoH2.fecharConexao;

public s class VisitanteDao {
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


	public void inserirVisitante(Visitante visitante) {
		String create = "insert into contatos (nome,fone,email) values (?,?,?)";
		try {
			Connection connection = abrirConexao();
			PreparedStatement pst = connection.prepareStatement(create);
			pst.setString(1, visitante.getNome());
			pst.setString(2, visitante.getFone());
			pst.setString(3, visitante.getEmail());
			pst.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ArrayList<Visitante> listarVisitantes() {
		ArrayList<Visitante> visitantes = new ArrayList<>();
		String read = "select * from visitantes order by nome";
		try {
			Connection connection = abrirConexao();
			PreparedStatement pst = connection.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				visitantes.add(new Visitante(idcon, nome, fone, email));
			}
			connection.close();
			return visitantes;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void selecionarVisitante(Visitante visitante) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection connection = abrirConexao();
			PreparedStatement pst = connection.prepareStatement(read2);
			pst.setString(1, visitante.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				visitante.setIdcon(rs.getString(1));
				visitante.setNome(rs.getString(2));
				visitante.setFone(rs.getString(3));
				visitante.setEmail(rs.getString(4));
			}
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void alterarVisitante(Visitante visitante) {
		String update = "update contatos set nome=?,fone=?,email=? where idcon=?";
		try {
			Connection connection = abrirConexao();
			PreparedStatement pst = connection.prepareStatement(update);
			pst.setString(1, visitante.getNome());
			pst.setString(2, visitante.getFone());
			pst.setString(3, visitante.getEmail());
			pst.setString(4, visitante.getIdcon());
			pst.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deletarVisitante(Visitante visitante) {
		String delete = "delete from contatos where idcon=?";
		try {
			Connection connection = abrirConexao();
			PreparedStatement pst = connection.prepareStatement(delete);
			pst.setString(1, visitante.getIdcon());
			pst.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
