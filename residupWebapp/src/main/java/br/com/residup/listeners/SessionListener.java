package br.com.residup.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class SessionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Enumeration<String> sessionIds = servletContext.getAttributeNames();

        while (sessionIds.hasMoreElements()) {
            String sessionId = sessionIds.nextElement();
            if (sessionId.startsWith("javax.servlet.http.HttpSession")) {
                HttpSession session = (HttpSession) servletContext.getAttribute(sessionId);
                session.invalidate();
            }
        }
    }
}