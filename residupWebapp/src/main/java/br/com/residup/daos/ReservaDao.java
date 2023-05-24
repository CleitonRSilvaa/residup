package br.com.residup.daos;



import br.com.residup.models.Convidado;
import br.com.residup.models.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
        String query = "SELECT ID_MORADOR FROM RESERVA_AREA WHERE ID_MORADOR  = ? AND DATA_RESERVA = ? ;";
        try {
            Connection connection = abrirConexao();

            PreparedStatement pst = connection.prepareStatement(query);
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

    public Reserva reserva(int id_reserva) throws SQLException {
        boolean retorno = false;
        String query = "SELECT ID_RESERVA_AREA, DATA_RESERVA FROM RESERVA_AREA WHERE ID_RESERVA_AREA = ?;";
        var reserva = Reserva.builder().build();
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id_reserva);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                reserva = Reserva.builder().dateReserva(rs.getString("DATA_RESERVA")).build();
            }

            connection.close();
            return reserva ;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar query TemReserva() ");

        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
            return reserva;
        }

    }

    public boolean TemReserva(Reserva reserva) throws SQLException {
        boolean retorno = false;
        String query = "SELECT * FROM RESERVA_AREA WHERE  " +
                "DATA_RESERVA = ?  " +
                "AND HORA_RESERVA  = ?  " +
                "AND ID_AREA = ?;";
        try {
            Connection connection = abrirConexao();

            PreparedStatement pst = connection.prepareStatement(query);
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
    public boolean insertConviado(Convidado convidado) {
        Boolean retorno = false;
        String create = "INSERT INTO CONVIDADO_RESERVA (NOME, DOCUMENTO, ID_RESERVA_AREA, ID_MORADOR)\n" +
                "VALUES ( ?, ?, ?, ?);";
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(create);
            pst.setString(1, convidado.getNome());
            pst.setString(2, convidado.getIndentidade());
            pst.setInt(3, convidado.getId_reserva());
            pst.setInt(4, convidado.getId_morador());

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


    public boolean deleteConvidado(int id_convidado) {
        Boolean retorno = false;
        String create = "DELETE FROM CONVIDADO_RESERVA WHERE ID_CONVIDADO = ? ;";
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(create);
            pst.setInt(1, id_convidado);

            int linhasRetorno =  pst.executeUpdate();
            connection.close();
            return linhasRetorno > 0;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar delete  convidado ");
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return retorno;
        }
    }

    public boolean temConvidado(int reserva) {
        Boolean retorno = false;
        String create = "SELECT ID_RESERVA_AREA FROM CONVIDADO_RESERVA WHERE ID_RESERVA_AREA = ? ;";
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(create);
            pst.setInt(1, reserva);
            ResultSet rs = pst.executeQuery();
            retorno = rs.next();
            connection.close();
            return retorno;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar query  temConvidado ");
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return retorno;
        }
    }

    public boolean deleteConvidadosEventos(int reserva) {
        Boolean retorno = false;
        String create = "DELETE FROM CONVIDADO_RESERVA WHERE ID_RESERVA_AREA = ? ;";
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(create);
            pst.setInt(1, reserva);

            int linhasRetorno =  pst.executeUpdate();
            connection.close();
            return linhasRetorno > 0;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar delete  convidado ");
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return retorno;
        }
    }

    public List areas() throws SQLException {
        List <Reserva> areaList = new ArrayList<>();
        String query = "SELECT * FROM AREA;" ;
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(query);

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
            throw new RuntimeException("----->>>> Erro ao executar query areas() ");

        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
            return areaList;
        }

    }

    public List reservas(int id_morador) throws SQLException {

        List <Reserva> reservaList = new ArrayList<>();
        String query = "SELECT * FROM RESERVA_AREA  R \n" +
                "INNER JOIN  AREA  A\n" +
                "ON R.ID_AREA= A.ID_AREA\n" +
                "WHERE ID_MORADOR = ?;";
        try {
            Connection connection = abrirConexao();

            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id_morador);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String dataString  = rs.getString(2);
                String hora = rs.getString(3);
                String area = rs.getString(7);
                DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                LocalDate data = LocalDate.parse(dataString, formatoEntrada);
                String dataFormatada = formatoSaida.format(data);
                var reserva = Reserva.builder()
                        .idReserva(id)
                        .dateReserva(dataFormatada)
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

    public List allReservas(String dataFiltro) throws SQLException, ParseException {
        List <Reserva> reservaList = new ArrayList<>();
        LocalDate dataAtual = LocalDate.now();
        String resultado = (dataFiltro == null) ? dataAtual.toString() : dataFiltro;

        String query = "SELECT ID_RESERVA_AREA, M.NOME AS NOME_MORADOR, SOBRENOME, BLOCO,  \n" +
                "NUMERO_APARTAMENTO,  A.NOME AS  NOME_AREA, DATA_RESERVA, HORA_RESERVA \n" +
                "FROM RESERVA_AREA  R\n" +
                "INNER JOIN AREA A ON R.ID_AREA= A.ID_AREA\n" +
                "INNER JOIN MORADOR M ON M.ID_MORADOR = R.ID_MORADOR\n" +
                "WHERE DATA_RESERVA = '" +resultado+ "';";
        try {
            Connection connection = abrirConexao();

            PreparedStatement pst = connection.prepareStatement(query);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID_RESERVA_AREA");
                String nome= rs.getString("NOME_MORADOR");
                String sobrenome = rs.getString("SOBRENOME");
                String dataString  = rs.getString(2);
                String hora = rs.getString(3);
                String area = rs.getString(7);
                DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                LocalDate data = LocalDate.parse(dataString, formatoEntrada);
                String dataFormatada = formatoSaida.format(data);
                var reserva = Reserva.builder()
                        .idReserva(id)
                        .dateReserva(dataFormatada)
                        .horaReserva(hora)
                        .nomeArea(area).build();

                reserva.getMorador().setNome("");
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



    public List convidados(int id_morador, int id_reserva) throws SQLException {

        List <Convidado> convidados = new ArrayList<>();
        String query = "SELECT * FROM CONVIDADO_RESERVA \n" +
                "WHERE ID_MORADOR = ? AND ID_RESERVA_AREA = ?;";
        try {
            Connection connection = abrirConexao();

            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, id_morador);
            pst.setInt(2, id_reserva);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nome  = rs.getString(2);
                String identidade = rs.getString(3);
                var convidado = new Convidado(id,nome,identidade,id_morador,id_reserva);
                convidados.add(convidado);

            }

            connection.close();
            return convidados;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar query convidados() ");

        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
            return convidados;
        }

    }




}
