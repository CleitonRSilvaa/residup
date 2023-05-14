package br.com.residup.daos;

import br.com.residup.models.Morador;
import br.com.residup.models.PerfilMorador;

import java.sql.*;
import java.util.ArrayList;

import static br.com.residup.shared.GerenciadorConexaoH2.abrirConexao;

public class PerfilMoradorDao {

    private Connection connection;
    ResultSet rs;
    ArrayList<PerfilMorador> listPerfilMorador = new ArrayList<>();
    public ArrayList <PerfilMorador> encontrarMorador() throws SQLException, ClassNotFoundException {

        String comandoSQL = "SELECT * FROM Morador";
        try{

            this.connection = abrirConexao();
            PreparedStatement instrucaoSQL = connection.prepareStatement(comandoSQL,
                    Statement.RETURN_GENERATED_KEYS);
            rs = instrucaoSQL.executeQuery(comandoSQL);

                PerfilMorador perfilMorador = new PerfilMorador();
                perfilMorador.setNome(rs.getString("NOME"));
                perfilMorador.setCpf(rs.getString("CPF"));
                perfilMorador.setRg(rs.getString("RG"));
                perfilMorador.setNumeroApartamento(rs.getString("NUMERO_APARTAMENTO"));
                perfilMorador.setBloco(rs.getString("BLOCO"));
                perfilMorador.setSenhaDeAcesso(rs.getString("SENHA_ACESSO"));

                listPerfilMorador.add(perfilMorador);

        }catch (SQLException e){


        }

        System.out.println("Sucesso no select");

        connection.close();

        return listPerfilMorador;
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
