package br.com.residup.servlets;

import br.com.residup.daos.MoradorDao;
import br.com.residup.models.Morador;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Logger;


@WebServlet("/create_morador")
public class CadastroMorador extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CadastroMorador.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var nomeCompleto = request.getParameter("nome");
        var cpf = request.getParameter("cpf");
        var rg = request.getParameter("rg");
        var bloco = request.getParameter("bloco");
        var email = request.getParameter("email");
        var telefone = request.getParameter("telefone");
        var numero_apartamento = request.getParameter("numeroApartamento");
        var senha = request.getParameter("senha");
        PrintWriter out = response.getWriter();

        try {
            String[] nomeSobrenome = Morador.separarNomeSobrenome(nomeCompleto);
            String nome = nomeSobrenome[0];
            String sobrenome = nomeSobrenome[1];
            var morador = new Morador(nome,sobrenome,cpf,rg,numero_apartamento,bloco,senha);
            if  (MoradorDao.createMorador(morador)){
                response.setContentType("text/html");
                out.println("<br><br><br><h1 align=center><font color=\"red\">TRY AGAIN<br>REDIRECTING BACK REGISTERATION PAGE</font></h1><script type=\"text/javascript\">");
                out.println("redirectURL = \"cadastroMorador.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");
                out.println("</script>");
            }
            request.setAttribute("status", "SUCCESS");//ou session.setAttribute("status", "ERROR");
            request.setAttribute("mensagem", "Morador Cadastardo com sucesso");//ou session.setAttribute("mensagem", "Nome já inserido. Informe outro");
            request.getRequestDispatcher("cadastroMorador.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
//            request.setAttribute("status", "ERROR");
//            request.setAttribute("mensagem", "Nome já inserido. Informe outro");
////            System.out.println(e.getMessage());
////            request.setAttribute("mensagem", "Ocorreu um erro durante o cadastro do morador."); // definir atributo com mensagem de erro
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroMorador.jsp"); // obter o dispatcher da página JSP
//            dispatcher.forward(request, response);
            response.setContentType("text/html");
            out.println("<br><br><br><h1 align=center><font color=\"red\">TRY AGAIN<br>REDIRECTING BACK REGISTERATION PAGE</font></h1><script type=\"text/javascript\">");
            out.println("redirectURL = \"cadastroMorador.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");
            out.println("</script>");
        }

    }

}
