package br.com.residup.servlets;

import br.com.residup.daos.MoradorDao;
import br.com.residup.models.Morador;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/create_morador")
public class CadastroMorador extends HttpServlet {
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

        try {
            String[] nomeSobrenome = Morador.separarNomeSobrenome(nomeCompleto);
            String nome = nomeSobrenome[0];
            String sobrenome = nomeSobrenome[1];
            var morador = new Morador(nome,sobrenome,cpf,rg,numero_apartamento,bloco,senha);
            if(MoradorDao.createMorador(morador)){
                request.getRequestDispatcher("CadastrarMorador.html").forward(request, response);
            }
        }catch (Exception e){
            System.out.println(e);
            request.getRequestDispatcher("CadastrarMorador.html").forward((ServletRequest) request, (ServletResponse) e);
        }

    }

}
