package br.com.residup.servlets;

import org.h2.tools.RunScript;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static br.com.residup.shared.GerenciadorConexaoH2.abrirConexao;


@WebServlet(urlPatterns="/createTablesH2")
public class CreateTablesH2 extends HttpServlet {
    private Connection connection = null;

    @Override
    public void init() throws ServletException {
        System.out.println("CreateTablesH2On initializeing on CreateTablesH2 on startup!!");

        try {
            connection = abrirConexao();
            ClassLoader classLoader = getClass().getClassLoader();
            URL resourceURL = classLoader.getResource("scritpTabelsH2.sql");
            String filePath = resourceURL.getPath();
            RunScript.execute(connection, new java.io.FileReader(filePath));
            System.out.println("Tabelas criadas com sucesso.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao criar as tabelas: ------>\n" + e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
