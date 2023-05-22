package br.com.residup.listeners;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionTimeoutListener implements Filter {
    private int sessionTimeout; // Tempo limite em segundos

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String timeout = filterConfig.getInitParameter("sessionTimeout");
        sessionTimeout = Integer.parseInt(timeout) * 60; // Converter minutos para segundos
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        if (session != null && isSessionExpired(session)) {
            session.invalidate();
            redirectToLoginPage(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private boolean isSessionExpired(HttpSession session) {
        long lastAccessTime = session.getLastAccessedTime();
        long currentTime = System.currentTimeMillis();
        long sessionDuration = (currentTime - lastAccessTime) / 1000; // Converter milissegundos para segundos
        return sessionDuration >= sessionTimeout;
    }

    private void redirectToLoginPage(ServletRequest request, ServletResponse response) throws IOException {
        String contextPath = request.getServletContext().getContextPath();
        ((HttpServletResponse) response).sendRedirect(contextPath + "/index");
    }
}
