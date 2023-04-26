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

    public static boolean logar(Morador morador) {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        boolean retorno = false;
        try (Connection connection = abrirConexao();
             PreparedStatement instrucao = connection
                .prepareStatement("SELECT * FROM MORADOR WHERE CPF = ? AND SENHA_ACESSO = ?")) {
            instrucao.setString(1, morador.getCpf());
            instrucao.setString(2, passwordEncryptor.encryptPassword(morador.getSenhaDeAcesso()));

            int linhasRetorno = instrucao.executeUpdate();

            if (linhasRetorno > 0) {
                retorno = true;
            } else {
                retorno = false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return retorno;
    }
}
