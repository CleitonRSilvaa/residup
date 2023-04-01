package br.com.residup.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GerenciadorConexaoDBMySql {
    public static  String status = "Não conectado";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String SERVER = "localhost";
    public static final String DATABASE = "residup";              //nome do seu banco de dados
    public static final String LOGIN = "root";                     //nome de um usuário de seu BD
    public static final String SENHA = "senha";                         //sua senha de acesso
    public static  String url = "";
    public static Connection connection;

    public GerenciadorConexaoDBMySql(){}

    public static Connection abrirConexao() throws ClassNotFoundException,SQLException {
        url = "jdbc:mysql://" + SERVER + ":3306/" +DATABASE + "?useTimezone=true&serverTimezone=UTC";
        if(connection ==null)
        {
            try {
                //Carrega a classe responsável pelo driver
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(url, LOGIN, SENHA);

                if (connection != null) {
                    status = "Conexão realizada com sucesso!";
                } else {
                    status = "Não foi possivel realizar a conexão";
                }

            } catch (ClassNotFoundException e) {  //Driver não encontrado
                throw new ClassNotFoundException("O driver expecificado nao foi encontrado.");
            } catch (SQLException e) {  //Erro ao estabelecer a conexão (Ex: login ou senha errados)
                //Outra falha de conexão
                throw new SQLException("Erro ao estabelecer a conexão (Ex: login ou senha errados).");
            }

        }
        else
        {
            try {
                //Se a conexão estiver fechada, reabro a conexão
                if(connection.isClosed())
                    connection = DriverManager.getConnection(url, LOGIN, SENHA);
            } catch (SQLException ex) {
                throw new SQLException("Falha ao fechar a conexão.");
            }
        }
        return connection;
    }

    public static String getStatusConexao() {
        return status;
    }
    public static boolean fecharConexao() throws SQLException {
        boolean retorno = false;
        try {
            if (connection == null) {
            } else {
                if(!connection.isClosed())
                    connection.close();
            }
            status = "Não conectado";
            retorno = true;

        } catch (SQLException e) {
            retorno = false;
        }
        return retorno;
    }

}
