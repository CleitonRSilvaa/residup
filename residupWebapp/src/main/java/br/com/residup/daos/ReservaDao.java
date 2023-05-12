package br.com.residup.daos;



import br.com.residup.models.Reserva;
import br.com.residup.models.Visitante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.com.residup.shared.GerenciadorConexaoH2.abrirConexao;

public class ReservaDao {

    private static ReservaDao instance;
    private Connection connection;

    private ReservaDao() {
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

    public static ReservaDao getInstance() {
        if (instance == null) {
            instance = new ReservaDao();
        }
        return instance;
    }

    public boolean moradorTemRederva(int id_morador) throws SQLException {
        boolean retorno = false;
        String qurey = "SELECT ID_MORADOR FROM RESERVA_AREA WHERE ID_MORADOR  = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(qurey);
            pst.setInt(1, id_morador);
            ResultSet rs = pst.executeQuery();
            retorno = rs.next();

            connection.close();
            return retorno;
        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
            return retorno;
        }

    }


    public boolean TemReserva(Reserva reserva) throws SQLException {
        boolean retorno = false;
        String qurey = "SELECT * FROM RESERVA_AREA WHERE  " +
                "DATA_RESERVA = ?  " +
                "AND HORA_RESERVA  = ?  " +
                "AND ID_AREA = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(qurey);
            pst.setString(1, reserva.getDate_reserva());
            pst.setString(2, reserva.getHora_reserva());
            pst.setInt(3, reserva.getId_morador());
            ResultSet rs = pst.executeQuery();
            retorno = rs.next();

            connection.close();
            return retorno;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar query TemReserva() ");

        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
            return retorno;
        }

    }

    public boolean insertReserva(Reserva reserva) {
        Boolean retorno = false;
        String create = "INSERT INTO RESERVA_AREA (DATA_RESERVA, HORA_RESERVA, ID_MORADOR, ID_AREA)\n" +
                "VALUES (TIMESTAMP ?, ?, ?, ?);";
        try {
//            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(create);
            pst.setString(1, reserva.getDate_reserva());
            pst.setString(2, reserva.getHora_reserva());
            pst.setInt(3, reserva.getId_morador());
            pst.setInt(4, reserva.getId_area());

            int linhasRetorno =  pst.executeUpdate();
            connection.close();
            return linhasRetorno > 0;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar insert reserva ");
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return retorno;
        }
    }
    public boolean deleteReserva(Reserva reserva) {
        Boolean retorno = false;
        String create = "DELETE * FROM RESERVA_AREA WHERE ID_RESERVA = ? AND ID_MORADOR = ?);";
        try {
//            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(create);
            pst.setString(1, reserva.getDate_reserva());
            pst.setString(2, reserva.getHora_reserva());
            pst.setInt(3, reserva.getId_morador());
            pst.setInt(4, reserva.getId_area());

            int linhasRetorno =  pst.executeUpdate();
            connection.close();
            return linhasRetorno > 0;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar insert reserva ");
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return retorno;
        }
    }



}
