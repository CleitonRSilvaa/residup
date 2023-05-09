package br.com.residup.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Filtro implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession sessao = req.getSession(false);

        // Verifica se a URL da solicitação é relacionada ao processo de login
        String loginURL = req.getContextPath() + "/login"; // Substitua "/login" pela URL real do processo de login
        boolean isLoginRequest = req.getRequestURI().equals(loginURL);

        if (sessao == null || sessao.getAttribute("cpf") == null) {
            if (isLoginRequest) {
                // Permite que a solicitação de login prossiga normalmente sem redirecionamento
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("login.jsp");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
