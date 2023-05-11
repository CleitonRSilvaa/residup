package br.com.residup.servlets;

import br.com.residup.daos.LoginDao;
import br.com.residup.models.Morador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        Morador morador = new Morador(cpf, senha);

        LoginDao loginDao = LoginDao.getInstance();
        boolean loginValido = loginDao.logar(morador);

        if (loginValido) {
            HttpSession session = request.getSession();
            session.setAttribute("cpf", cpf);
            response.sendRedirect("/visitantes");
//            String idzao = loginDao.recuperarId(cpf);
            System.out.println("");
            System.out.println("select return id");
            session.setAttribute("id_morador", "");
            System.out.println("Login finalizado com sucesso!");
        } else {
            System.out.println("Login não encontrado/incorreto");
            request.setAttribute("error", "CPF e/ou senha incorretos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }




}