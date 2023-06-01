package br.com.residup.daos;

import br.com.residup.models.Morador;
import br.com.residup.models.PerfilMorador;
import br.com.residup.models.Visitante;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.com.residup.shared.GerenciadorConexaoH2.abrirConexao;

public class MoradorDao {

    private static MoradorDao instance;


    private Connection connection;

    public MoradorDao() {
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

    public static MoradorDao getInstance() {
        if (instance == null) {
            instance = new MoradorDao();
        }
        return instance;
    }

    public static boolean createMorador(Morador morador) {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        boolean retorno = false;

        try (Connection connection = abrirConexao();
             PreparedStatement instrucaoSQL = connection.prepareStatement(
                     "INSERT INTO MORADOR (NOME, SOBRENOME, CPF, RG, NUMERO_APARTAMENTO, BLOCO, SENHA_ACESSO, DATA_INCERCAO, FOTO_FILE_PATH) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ? )",
                     Statement.RETURN_GENERATED_KEYS)
        ) {
            instrucaoSQL.setString(1, morador.getNome());
            instrucaoSQL.setString(2, morador.getSobrenome());
            instrucaoSQL.setString(3, morador.getCpf());
            instrucaoSQL.setString(4, morador.getRg());
            instrucaoSQL.setString(5, morador.getNumeroApartamento());
            instrucaoSQL.setString(6, morador.getBloco());
            instrucaoSQL.setString(7, passwordEncryptor.encryptPassword(morador.getSenhaDeAcesso()));
            instrucaoSQL.setString(8, morador.getEnderecoFoto());
            System.out.println(morador);
            int linhasRetorno = instrucaoSQL.executeUpdate();

            if (linhasRetorno > 0) {
                retorno = true;

                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys();
                if (generatedKeys.next()) {
                    morador.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o ID do morador.");
                }
            } else {
                retorno = false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return retorno;
    }


    public ArrayList <Morador> listarMoradores(String cpfFiltro) throws SQLException, ClassNotFoundException {

        String extensaoSelect = "";
        String filtro = (cpfFiltro.equals("")) ? "" : " WHERE CPF= " + cpfFiltro;
        System.out.println(cpfFiltro);
        ArrayList<Morador> listaMoradores = new ArrayList<>();
        String comandoSQL = "SELECT * FROM Morador" + filtro;
        try{
            this.connection = abrirConexao();
            PreparedStatement instrucaoSQL = connection.prepareStatement(comandoSQL);
            ResultSet rs = instrucaoSQL.executeQuery();

            while(rs.next()){

            String nome = (rs.getString("NOME"));
            String sobrenome = (rs.getString("SOBRENOME"));
            String cpf = (rs.getString("CPF"));
            String numApart= (rs.getString("NUMERO_APARTAMENTO"));
            String bloco = (rs.getString("BLOCO"));
            String status = (rs.getString("STATUS_CONTA"));


            var morador = new Morador(nome, sobrenome, cpf, null, numApart, bloco, null);
            morador.setStatus(status);
            listaMoradores.add(morador);

            }
            connection.close();
            return listaMoradores;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar query Listar Moradores() ");

        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
            return listaMoradores;
        }

    }

    public  Morador buscarMorador(String CPF){

        String comandoSQL = "SELECT * FROM Morador WHERE CPF=?";

        Morador morador = new Morador(null, null, null, null, null, null, null);
        try{
            this.connection = abrirConexao();
            PreparedStatement instrucaoSQL = connection.prepareStatement(comandoSQL);
            instrucaoSQL.setString(1, CPF);
            ResultSet rs = instrucaoSQL.executeQuery();

            if(rs.next()){

                int id = rs.getInt("ID_MORADOR");
                String nome = rs.getString("NOME");
                String sobrenome = rs.getString("SOBRENOME");
                String cpf = rs.getString("CPF");
                String rg = rs.getString("RG");
                String numApartamento = rs.getString("NUMERO_APARTAMENTO");
                String bloco = rs.getString("BLOCO");
                String enderecoFoto = rs.getString("FOTO_FILE_PATH");

                Morador morador1 = new Morador(nome, sobrenome, cpf, rg, numApartamento, bloco, null);
                morador1.setEnderecoFoto(enderecoFoto);

                morador = morador1;
            }
            connection.close();
            return morador;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar query Buscar Morador() ");

        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
            return morador;
        }


    }

    public static Boolean updateMoradorPerfil(Morador morador){

        String updateMorador = "UPDATE MORADOR SET SENHA_ACESSO= ?, DATA_ALTERACAO= CURRENT_TIMESTAMP, FOTO_FILE_PATH= ? WHERE CPF=?";
        try{
            Connection connection = abrirConexao();
            PreparedStatement upd = connection.prepareStatement(updateMorador);
            upd.setString(1,morador.getSenhaDeAcesso());
            upd.setString(2,morador.getEnderecoFoto());
            upd.setString(3,morador.getCpf());
            upd.executeUpdate();
            return true;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar query Update Perfil Morador()");

        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    public static Boolean editarMorador(Morador morador){
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

        String editarMorador = "UPDATE MORADOR SET NOME= ?, SOBRENOME= ?, CPF= ?, RG= ?, NUMERO_APARTAMENTO= ?, BLOCO= ?, SENHA_ACESSO= ?, DATA_ALTERACAO= CURRENT_TIMESTAMP, FOTO_FILE_PATH= ? WHERE CPF=?";
        try{
            Connection connection = abrirConexao();
            PreparedStatement upd = connection.prepareStatement(editarMorador);
            upd.setString(1,morador.getNome());
            upd.setString(2,morador.getSobrenome());
            upd.setString(3,morador.getCpf());
            upd.setString(4,morador.getRg());
            upd.setString(5,morador.getNumeroApartamento());
            upd.setString(6,morador.getBloco());
            upd.setString(7, passwordEncryptor.encryptPassword(morador.getSenhaDeAcesso()));
            upd.setString(8,morador.getEnderecoFoto());
            upd.setString(9,morador.getCpf());
            upd.executeUpdate();
            return true;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar query Editar Morador()");

        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    public static Boolean checkCadastro(String CPF){

        Boolean result = false;
        String comandoSQL = "SELECT * FROM Morador WHERE CPF=?";


        try{
            Connection connection = abrirConexao();
            PreparedStatement instrucaoSQL = connection.prepareStatement(comandoSQL);
            instrucaoSQL.setString(1, CPF);
            ResultSet rs = instrucaoSQL.executeQuery();

            result = rs.next();

            connection.close();
            return result;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getLocalizedMessage());
            throw new RuntimeException("----->>>> Erro ao executar query Buscar Morador() ");

        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
            return result;
        }


    }

    public static boolean deletarMorador(Morador morador) {
        String delete = "UPDATE MORADOR SET STATUS_CONTA = 0 WHERE CPF=?";
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(delete);
            pst.setString(1, morador.getCpf());
            pst.executeUpdate();
            if (!connection.isClosed()){
                connection.close();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    public static boolean ativarMorador(Morador morador) {
        String ativar = "UPDATE MORADOR SET STATUS_CONTA = 1 WHERE CPF=?";
        try {
            Connection connection = abrirConexao();
            PreparedStatement pst = connection.prepareStatement(ativar);
            pst.setString(1, morador.getCpf());
            pst.executeUpdate();
            if (!connection.isClosed()){
                connection.close();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }



}
