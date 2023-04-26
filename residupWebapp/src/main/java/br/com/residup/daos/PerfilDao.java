package br.com.residup.daos;

import br.com.residup.models.Perfil;

import java.sql.*;
import java.util.ArrayList;

import static br.com.residup.shared.GerenciadorConexaoH2.abrirConexao;

public class PerfilDao {

    private Connection connection;
    ResultSet rs;
    ArrayList<Perfil> listPerfil = new ArrayList<>();
    public ArrayList <Perfil> encontrarPerfil() throws SQLException, ClassNotFoundException {

        String comandoSQL = "SELECT * FROM Morador";
        try{
            Connection connection = abrirConexao();
            PreparedStatement instrucaoSQL = connection.prepareStatement(comandoSQL,
                    Statement.RETURN_GENERATED_KEYS);
            rs = instrucaoSQL.executeQuery(comandoSQL);

                Perfil perfil = new Perfil();
                perfil.setNome(rs.getString("NOME"));
                perfil.setCpf(rs.getString("CPF"));
                perfil.setRg(rs.getString("RG"));
                perfil.setNumeroApartamento(rs.getString("NUMERO_APARTAMENTO"));
                perfil.setBloco(rs.getString("BLOCO"));
                perfil.setSenhaDeAcesso(rs.getString("SENHA_ACESSO"));

                listPerfil.add(perfil);

        }catch (SQLException | ClassNotFoundException e){


        }

        System.out.println("Sucesso no select");

        connection.close();

        return listPerfil;
    }
}
