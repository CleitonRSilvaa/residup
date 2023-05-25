package br.com.residup.daos;

import br.com.residup.models.Ocorrencia;
import br.com.residup.models.OcorrenciaBuilder;
import br.com.residup.models.Status;

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
        try {
            Connection connection = abrirConexao();
            PreparedStatement instrucao = connection.prepareStatement("INSERT INTO REGISTRO_OCORRENCIA (TITULO, OCORRENCIA, ID_MORADOR, STATUS, DATA_REGISTRO_OCORRENCIA ) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)");
            instrucao.setString(1, ocorrencia.getTitulo());
            instrucao.setString(2, ocorrencia.getTexto());
            instrucao.setInt(3, ocorrencia.getId_morador());
            instrucao.setString(4, Status.EM_ABERTO.getStatus());

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


    public static ArrayList<Ocorrencia> listarDoMorador(int id_morador) {
        try (Connection connection = abrirConexao();
             PreparedStatement instrucao = connection
                     .prepareStatement("SELECT * FROM REGISTRO_OCORRENCIA WHERE ID_MORADOR= ?")) {
            instrucao.setInt(1, id_morador);
            ArrayList<Ocorrencia> ocorrencias = new ArrayList<>();
            ResultSet rs = instrucao.executeQuery();
            while (rs.next()) {
                int id_ocorrencia = rs.getInt("ID_OCORRENCIA");
                String titulo = rs.getString("TITULO");
                String texto = rs.getString("OCORRENCIA");
                String status = rs.getString("STATUS");
                var registroOcorrencia = Ocorrencia.builder().id_ocorrencia(id_ocorrencia).titulo(titulo).texto(texto).status(status).build();
                ocorrencias.add(registroOcorrencia);
            }
            return ocorrencias;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ArrayList<Ocorrencia> listarDoMoradorSindico() {
        try (Connection connection = abrirConexao();
             PreparedStatement instrucao = connection
                     .prepareStatement("SELECT *\n" +
                             "FROM REGISTRO_OCORRENCIA\n" +
                             "INNER JOIN MORADOR ON REGISTRO_OCORRENCIA.id_morador = MORADOR.id_morador;\n")) {
            ArrayList<Ocorrencia> ocorrencias = new ArrayList<>();
            ResultSet rs = instrucao.executeQuery();
            while (rs.next()) {
                int id_ocorrencia = rs.getInt("ID_OCORRENCIA");
                String titulo = rs.getString("TITULO");
                String texto = rs.getString("OCORRENCIA");
                String status = rs.getString("STATUS");
                String nomeMorador = rs.getString("NOME");
                String numeroApartamento = rs.getString("NUMERO_APARTAMENTO");
                String bloco = rs.getString("BLOCO");
                var registroOcorrencia = Ocorrencia.builder().id_ocorrencia(id_ocorrencia).titulo(titulo).texto(texto).status(status).build();
                registroOcorrencia.setNome(nomeMorador);
                registroOcorrencia.setNumeroApartamento(numeroApartamento);
                registroOcorrencia.setBloco(bloco);
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
            pst.setInt(1, ocorrencia.getId_ocorrencia());
            pst.executeUpdate();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static Ocorrencia getOcorrencia(int id_ocorrencia) {
        String select = "SELECT * FROM REGISTRO_OCORRENCIA  RO\n" +
                "INNER JOIN MORADOR M\n" +
                "ON RO.ID_MORADOR = M.ID_MORADOR " +
                "WHERE ID_OCORRENCIA = ? ";
        Ocorrencia ocorrencia = new Ocorrencia();
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(select);
            pst.setInt(1, id_ocorrencia);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id_ocorrenciaResult = rs.getInt("ID_OCORRENCIA");
                String titulo = rs.getString("TITULO");
                String texto = rs.getString("OCORRENCIA");
                String status = rs.getString("STATUS");
                String nomeMorador = rs.getString("NOME");
                String numeroApartamento = rs.getString("NUMERO_APARTAMENTO");
                String bloco = rs.getString("BLOCO");

                var registroOcorrencia = Ocorrencia.builder()
                        .id_ocorrencia(id_ocorrenciaResult)
                        .titulo(titulo)
                        .texto(texto)
                        .id_ocorrencia(id_ocorrenciaResult)
                        .status(status)
                        .build();

                registroOcorrencia.setNome(nomeMorador);
                registroOcorrencia.setNumeroApartamento(numeroApartamento);
                registroOcorrencia.setBloco(bloco);

                ocorrencia = registroOcorrencia;
                System.out.println("esssssse" + ocorrencia);
            }
            connection.close();
            return ocorrencia;
        } catch (Exception e) {
            System.out.println(e);
            return ocorrencia;
        }
    }

    public static boolean resolver(Ocorrencia ocorrencia) {
        String update = "UPDATE REGISTRO_OCORRENCIA SET RESOLUCAO=? WHERE ID_OCORRENCIA=?";
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(update);
            pst.setString(1, ocorrencia.getStatus());
            pst.setInt(2, ocorrencia.getId_ocorrencia());
            pst.executeUpdate();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
