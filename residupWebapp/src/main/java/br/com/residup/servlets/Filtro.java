package br.com.residup.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Filtro implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        String requestURI = httpRequest.getRequestURI();
        boolean loggedIn = session != null && session.getAttribute("cpf") != null;
        boolean loginRequest = httpRequest.getRequestURI().equals("/index") || httpRequest.getRequestURI().equals("/UpdatePassword");;
        boolean isStaticResource = requestURI.endsWith(".css") || requestURI.startsWith("/imagens/img/");

        if (loggedIn || loginRequest || isStaticResource ) {
            chain.doFilter(request, response);
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
        } else {

           request.getRequestDispatcher("index.jsp").forward(request, response);

        }
    }




    @Override
    public void destroy() {

    }
}
