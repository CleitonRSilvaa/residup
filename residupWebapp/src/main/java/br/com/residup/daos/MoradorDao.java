package br.com.residup.daos;

import br.com.residup.models.Morador;
import br.com.residup.models.PerfilMorador;
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
                     "INSERT INTO MORADOR (NOME, SOBRENOME, CPF, RG, NUMERO_APARTAMENTO, BLOCO, SENHA_ACESSO, DATA_INCERCAO) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP )",
                     Statement.RETURN_GENERATED_KEYS)
        ) {
            instrucaoSQL.setString(1, morador.getNome());
            instrucaoSQL.setString(2, morador.getSobrenome());
            instrucaoSQL.setString(3, morador.getCpf());
            instrucaoSQL.setString(4, morador.getRg());
            instrucaoSQL.setString(5, morador.getNumeroApartamento());
            instrucaoSQL.setString(6, morador.getBloco());
            instrucaoSQL.setString(7, passwordEncryptor.encryptPassword(morador.getSenhaDeAcesso()));

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


    public ArrayList <PerfilMorador> listarMoradores() throws SQLException, ClassNotFoundException {

        ArrayList<PerfilMorador> listaMoradores = new ArrayList<>();
        String comandoSQL = "SELECT * FROM Morador";
        try{
            this.connection = abrirConexao();
            PreparedStatement instrucaoSQL = connection.prepareStatement(comandoSQL);
            ResultSet rs = instrucaoSQL.executeQuery();

            while(rs.next()){
            PerfilMorador perfilMorador = new PerfilMorador();
            perfilMorador.setNome(rs.getString("NOME"));
            perfilMorador.setCpf(rs.getString("CPF"));
            perfilMorador.setRg(rs.getString("RG"));
            perfilMorador.setNumeroApartamento(rs.getString("NUMERO_APARTAMENTO"));
            perfilMorador.setBloco(rs.getString("BLOCO"));
            perfilMorador.setSenhaDeAcesso(rs.getString("SENHA_ACESSO"));

            listaMoradores.add(perfilMorador);

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

    public Morador buscarMorador(String CPF){

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
                String enderecoFoto = rs.getString("ENDERECO_FOTO");

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

    public static Boolean alterarMorador(Morador morador){

        String updateMorador = "UPDATE MORADOR SET NOME =?, SET CPF=?, SET RG=?, SET NUMERO_APARTAMENTO=?, SET BLOCO=?, SET SENHA_ACESSO=?";
        try{
            Connection connection = abrirConexao();
            PreparedStatement upd = connection.prepareStatement(updateMorador);
            upd.setString(1, morador.getNome());
            upd.setString(2, morador.getCpf());
            upd.setString(3, morador.getRg());
            upd.setString(4, morador.getNumeroApartamento());
            upd.setString(5,morador.getBloco());
            upd.setString(6,morador.getSenhaDeAcesso());
            upd.executeUpdate();
            return true;
        }catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
