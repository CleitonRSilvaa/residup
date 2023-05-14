package br.com.residup.daos;



import br.com.residup.models.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public boolean moradorTemRederva(Reserva reserva) throws SQLException {
        boolean retorno = false;
        String qurey = "SELECT ID_MORADOR FROM RESERVA_AREA WHERE ID_MORADOR  = ? AND DATA_RESERVA = ? ;";
        try {
            Connection connection = abrirConexao();

            PreparedStatement pst = connection.prepareStatement(qurey);
            pst.setInt(1, reserva.getIdMorador());
            pst.setString(2, reserva.getDateReserva());

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
            Connection connection = abrirConexao();

            PreparedStatement pst = connection.prepareStatement(qurey);
            pst.setString(1, reserva.getDateReserva());
            pst.setString(2, reserva.getHoraReserva());
            pst.setInt(3, reserva.getIdArea());
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
                "VALUES ( ?, ?, ?, ?);";
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(create);
            pst.setString(1, reserva.getDateReserva());
            pst.setString(2, reserva.getHoraReserva());
            pst.setInt(3, reserva.getIdMorador());
            pst.setInt(4, reserva.getIdArea());

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
        String create = "DELETE FROM RESERVA_AREA WHERE ID_RESERVA_AREA = ? AND ID_MORADOR = ?;";
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(create);
            pst.setInt(1, reserva.getIdReserva());
            pst.setInt(2, reserva.getIdMorador());

            int linhasRetorno =  pst.executeUpdate();
            connection.close();
            return linhasRetorno > 0;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar delete  reserva ");
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return retorno;
        }
    }

    public List areas() throws SQLException {
        List <Reserva> areaList = new ArrayList<>();
        String qurey = "SELECT * FROM AREA;" ;
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(qurey);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nomeArea = rs.getString(2);
               var area = Reserva.builder()
                        .idArea(id)
                        .nomeArea(nomeArea).build();
                areaList.add(area);
            }
            connection.close();
            return areaList;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar query ares() ");

        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
            return areaList;
        }

    }

    public List reservas(int id_morador) throws SQLException {

        List <Reserva> reservaList = new ArrayList<>();
        String qurey = "SELECT * FROM RESERVA_AREA  R \n" +
                "INNER JOIN  AREA  A\n" +
                "ON R.ID_AREA= A.ID_AREA\n" +
                "WHERE ID_MORADOR = ?;";
        try {
            Connection connection = abrirConexao();

            PreparedStatement pst = connection.prepareStatement(qurey);
            pst.setInt(1, id_morador);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String data = rs.getString(2);
                String hora = rs.getString(3);
                String area = rs.getString(7);
                var reserva = Reserva.builder()
                        .idReserva(id)
                        .dateReserva(data)
                        .horaReserva(hora)
                        .nomeArea(area).build();
                reservaList.add(reserva);
            }

            connection.close();
            return reservaList;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar query TemReserva() ");

        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
            return reservaList;
        }

    }



}
