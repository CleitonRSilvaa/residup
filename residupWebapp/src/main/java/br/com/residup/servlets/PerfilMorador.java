package br.com.residup.servlets;


import br.com.residup.daos.PerfilMoradorDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/encontrar-perfil-morador")
public class PerfilMorador extends HttpServlet{
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            try {
                ArrayList<br.com.residup.models.PerfilMorador> moradorPerfil = new PerfilMoradorDao().encontrarMorador();

                req.setAttribute("moradorPerfil", moradorPerfil);

                req.getRequestDispatcher("testePerfil.jsp").forward(req, resp);

            } catch (SQLException | ClassNotFoundException e){


            }

        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
