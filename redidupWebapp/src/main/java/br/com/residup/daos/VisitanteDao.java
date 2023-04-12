package br.com.residup.daos;

import br.com.residup.models.Visitante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class VisitanteDao {

	private String driver = "com.mysql.cj.jdbc.Driver";
	
	private String url = "jdbc:mysql://127.0.0.1:3306/casa_construcao?useTimezone=true&serverTimezone=UTC";
	
	private String user = "root";
	
	private String password = "@@Brasil2022";

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void inserirVisitante(Visitante visitante) {
		String create = "insert into contatos (nome,fone,email) values (?,?,?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, visitante.getNome());
			pst.setString(2, visitante.getFone());
			pst.setString(3, visitante.getEmail());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ArrayList<Visitante> listarVisitantes() {
		ArrayList<Visitante> visitantes = new ArrayList<>();
		String read = "select * from visitantes order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				visitantes.add(new Visitante(idcon, nome, fone, email));
			}
			con.close();
			return visitantes;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void selecionarVisitante(Visitante visitante) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, visitante.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				visitante.setIdcon(rs.getString(1));
				visitante.setNome(rs.getString(2));
				visitante.setFone(rs.getString(3));
				visitante.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void alterarVisitante(Visitante visitante) {
		String update = "update contatos set nome=?,fone=?,email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, visitante.getNome());
			pst.setString(2, visitante.getFone());
			pst.setString(3, visitante.getEmail());
			pst.setString(4, visitante.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deletarVisitante(Visitante visitante) {
		String delete = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, visitante.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
