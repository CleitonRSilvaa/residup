package br.com.residup.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/enoc")
public class EnocServelt extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        if (name.equals("enoc")) {
            req.setAttribute("validator", true);
        } else {
            req.setAttribute("validator", false);

        }

        req.setAttribute("message", "Mensagem do professor");

        req.getRequestDispatcher("enoc.jsp").forward(req, resp);


    }
}
