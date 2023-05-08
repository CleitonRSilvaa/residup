package br.com.residup.daos;

import br.com.residup.models.Morador;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.com.residup.shared.GerenciadorConexaoH2.abrirConexao;

public class LoginDao {
    private static LoginDao instance;
    private Connection connection;

    private LoginDao() {
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

    public static LoginDao getInstance() {
        if (instance == null) {
            instance = new LoginDao();
        }
        return instance;
    }

    public boolean logar(Morador morador) {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        boolean retorno = false;
        try (
                PreparedStatement instrucao = connection.prepareStatement("SELECT * FROM MORADOR WHERE CPF = ?")
        ) {
            instrucao.setString(1, morador.getCpf());

            ResultSet rs = instrucao.executeQuery();

            if (rs.next() && passwordEncryptor.checkPassword(morador.getSenhaDeAcesso(), rs.getString("SENHA_ACESSO"))) {
                System.out.println("Morador encontrado!");
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
