package br.com.residup.daos;

import br.com.residup.models.PerfilDependente;

import java.sql.*;
import java.util.ArrayList;

public class PerfilDependenteDao {

    private Connection connection;

    ResultSet rs;

    ArrayList<PerfilDependente> listPerfilDependente = new ArrayList<>();

    public ArrayList <PerfilDependente> encontrarDependente() throws SQLException, ClassNotFoundException {

        String comandoSQL = "SELECT * FROM MORADOR_DEPENDENTE";
        try {

            PreparedStatement instrucaoSQL = connection.prepareStatement(comandoSQL,
                    Statement.RETURN_GENERATED_KEYS);
            rs = instrucaoSQL.executeQuery(comandoSQL);

            PerfilDependente perfilDependente = new PerfilDependente();
            perfilDependente.setNomeDependente(rs.getString("NOME"));
            perfilDependente.setDocumentoDependente(rs.getString("DOCUMENTO"));


            listPerfilDependente.add(perfilDependente);

        } catch (SQLException e) {


        }

        System.out.println("Sucesso no select");

        connection.close();

        return listPerfilDependente;
    }
}
