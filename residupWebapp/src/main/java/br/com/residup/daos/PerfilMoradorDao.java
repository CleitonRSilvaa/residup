package br.com.residup.daos;

import br.com.residup.models.PerfilMorador;

import java.sql.*;
import java.util.ArrayList;

public class PerfilMoradorDao {

    private Connection connection;
    ResultSet rs;
    ArrayList<PerfilMorador> listPerfilMorador = new ArrayList<>();
    public ArrayList <PerfilMorador> encontrarMorador() throws SQLException, ClassNotFoundException {

        String comandoSQL = "SELECT * FROM Morador";
        try{

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
}
