package br.com.residup.servlets;

import br.com.residup.daos.LoginDao;
import br.com.residup.models.Morador;
import br.com.residup.models.IconAlertJS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static br.com.residup.shared.Uteis.scriptMensagemAlertJs;

@WebServlet( urlPatterns = {"/index", "/UpdatePassword"})
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String  ultimoCpf = "";


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("cpf") != null && session.getAttribute("id_morador") != null) {
            response.sendRedirect("/Ocorrencia");
            return;
        }
        String mgs = (String) request.getSession().getAttribute("mensagemAlert");
        if (mgs != null) {
            request.setAttribute("mensagem", mgs);
        }


        String mgsErroSenha = (String) request.getSession().getAttribute("errorSenha");
        if (mgsErroSenha != null) {
            request.setAttribute("erro", mgsErroSenha);
        }

        String primeiroAcesso = (String) request.getSession().getAttribute("primeiroAcesso");
        if (primeiroAcesso != null) request.setAttribute("primeiroAcesso", true);

        request.getSession().removeAttribute("errorSenha");
        request.getSession().removeAttribute("primeiroAcesso");
        request.getSession().removeAttribute("mensagemAlert");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


    private void validarLogin(HttpServletRequest request, HttpServletResponse response){
        try {
            String cpf = request.getParameter("cpfLogin");
            String senha = request.getParameter("senhaLogin");
            Morador morador = new Morador(cpf, senha);
            LoginDao loginDao = LoginDao.getInstance();

            if (loginDao.validaPrimeiroAcesso(cpf)) {
                String scriptMensagem = scriptMensagemAlertJs(
                        IconAlertJS.warning,
                        "Sua senha é a padrão fornecida pelo síndico.",
                        "Para sua segurança, altere a senha!"
                );
                request.setAttribute("mensagemAlert", scriptMensagem);
                request.setAttribute("primeiroAcesso", true);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            boolean loginValido = loginDao.logar(morador);
            if (loginValido) {
                HttpSession session = request.getSession();
                session.setAttribute("cpf", cpf);
                session.setAttribute("id_morador", loginDao.recuperarId(cpf));
                response.sendRedirect("listarResumo");
            } else {
                System.out.println("Login não encontrado/incorreto");
                request.setAttribute("error", "Senha CPF ou senha incorretos.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }catch (Exception e){


        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String action = request.getServletPath();
            LoginDao loginDao = LoginDao.getInstance();

            if (action.equals("/UpdatePassword")){
                String cpfTemp = (String) request.getSession().getAttribute("cpfTemp");
                String senhaTemp = request.getParameter("senha");
                Morador morador = new Morador(cpfTemp, senhaTemp);
                request.getSession().removeAttribute("cpfTemp");
                if (loginDao.autualizarSenha(morador)){
                    String scriptMensagem = scriptMensagemAlertJs(
                            IconAlertJS.success,
                            "Senha atualizada com sucesso.",
                            "Efetue novo login!"
                    );
                    request.getSession().setAttribute("mensagemAlert", scriptMensagem);

                }else {
                    String scriptMensagem = scriptMensagemAlertJs(
                            IconAlertJS.error,
                            "Erro ao atualizar senha.",
                            "Contate o administrador!"
                    );
                    request.getSession().setAttribute("mensagemAlert", scriptMensagem);
                }
                response.sendRedirect("/index");
                return;

            }
            String cpf = request.getParameter("cpfLogin");
            String senha = request.getParameter("senhaLogin");
            Morador morador = new Morador(cpf, senha);

            if (loginDao.validaPrimeiroAcesso(cpf)) {

                String scriptMensagem = scriptMensagemAlertJs(
                        IconAlertJS.warning,
                        "Sua senha é a padrão fornecida pelo síndico.",
                        "Para sua segurança, altere a senha!"
                );
                request.getSession().setAttribute("cpfTemp", cpf);
                request.getSession().setAttribute("mensagemAlert", scriptMensagem);
                request.getSession().setAttribute("primeiroAcesso", "sim");
                response.sendRedirect("/index");
                return;
            }

            boolean loginValido = loginDao.logar(morador);
            if (loginValido) {
                HttpSession session = request.getSession();
                session.setAttribute("cpf", cpf);
                session.setAttribute("id_morador", loginDao.recuperarId(cpf));
                response.sendRedirect("listarResumo");
            } else {
                System.out.println("Login não encontrado/incorreto");
                request.getSession().setAttribute("errorSenha", "Senha CPF ou Senha incorretos.");
                response.sendRedirect("/index");
            }
        }catch (Exception e){

        }


    }


}
