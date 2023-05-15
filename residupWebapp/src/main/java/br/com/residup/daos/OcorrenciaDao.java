package br.com.residup.daos;

import br.com.residup.models.Ocorrencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.com.residup.shared.GerenciadorConexaoH2.abrirConexao;

public class OcorrenciaDao {
    private static OcorrenciaDao instance;
    private Connection connection;

    private OcorrenciaDao() {
        this.handleOpenConnection();
    }

    private void handleOpenConnection() {
        try {
            this.connection = abrirConexao();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static OcorrenciaDao getInstance() {
        if (instance == null) {
            instance = new OcorrenciaDao();
        }
        return instance;
    }

    public static boolean registrar(Ocorrencia ocorrencia) {
        boolean retorno = false;
        try {Connection connection = abrirConexao();
            PreparedStatement instrucao = connection.prepareStatement("INSERT INTO REGISTRO_OCORRENCIA (TITULO, OCORRENCIA, ID_MORADOR) VALUES (?, ?, ?)");
            instrucao.setString(1, ocorrencia.getTitulo());
            instrucao.setString(2, ocorrencia.getTexto());
            instrucao.setInt(3, ocorrencia.getId_morador());

            int linhasRetorno = instrucao.executeUpdate();
            return linhasRetorno > 0;

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static ArrayList<Ocorrencia> listar() {
        try (Connection connection = abrirConexao();
             PreparedStatement instrucao = connection
                     .prepareStatement("SELECT * FROM REGISTRO_OCORRENCIA")) {
            ArrayList<Ocorrencia> ocorrencias = new ArrayList<>();
            ResultSet rs = instrucao.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String titulo = rs.getString(2);
                String texto = rs.getString(3);
                String resolucao = rs.getString(4);
                int id_morador = rs.getInt(5);
                var registroOcorrencia = new Ocorrencia(titulo, texto, resolucao, id_morador);
                registroOcorrencia.setId(id);
                ocorrencias.add(registroOcorrencia);
            }
            return ocorrencias;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public static boolean deletar(Ocorrencia ocorrencia) {
        String delete = "DELETE FROM REGISTRO_OCORRENCIA WHERE ID_OCORRENCIA=?";
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(delete);
            pst.setInt(1, ocorrencia.getId());
            pst.executeUpdate();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean resolver(Ocorrencia ocorrencia) {
        String update = "UPDATE REGISTRO_OCORRENCIA SET RESOLUCAO=? WHERE ID_OCORRENCIA=?";
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(update);
            pst.setString(1, ocorrencia.getResolucao());
            pst.setInt(2, ocorrencia.getId());
            pst.executeUpdate();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean editar(Ocorrencia ocorrencia) {
        String update = "UPDATE REGISTRO_OCORRENCIA SET TITULO=?, OCORRENCIA=? WHERE ID_OCORRENCIA=?";
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(update);
            pst.setString(1, ocorrencia.getTitulo());
            pst.setString(2, ocorrencia.getTexto());
            pst.setInt(3, ocorrencia.getId());
            pst.executeUpdate();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static void selecionar(Ocorrencia ocorrencia) {
        String read2 = "SELECT * FROM REGISTRO_OCORRENCIA WHERE ID_OCORRENCIA=?";
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(read2);
            pst.setInt(1, ocorrencia.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ocorrencia.setTitulo(rs.getString("TITULO"));
                ocorrencia.setTexto(rs.getString("OCORRENCIA"));
                ocorrencia.setResolucao(rs.getString("RESOLUCAO"));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
