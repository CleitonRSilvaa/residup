package br.com.residup.servlets;

import br.com.residup.daos.LoginDao;
import br.com.residup.daos.OcorrenciaDao;
import br.com.residup.models.Ocorrencia;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/Ocorrencia", "/occurrenceInsert", "/occurrenceSelect", "/occurrenceUpdate", "/occurrenceDelete", "/occurrenceResolve"})
public class RegistroOcorrencia extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Ocorrencia ocorrencias = new Ocorrencia();

    public RegistroOcorrencia() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/occurrenceSelect")) {
            request.getSession().setAttribute("validator", true);
            listarOcorrencia(request, response);
            return;
        }
        ocorrencia(request, response);

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/occurrenceDelete")) {
            removerOcorrencia(request, response);
            return;
        }
        ocorrencia(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/occurrenceInsert")) {
            registrarOcorrencia(request, response);
            return;
        }
        ocorrencia(request, response);
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/occurrenceUpdate")) {
            editarOcorrencia(request, response);
            return;
        }
        if (action.equals("/occurrenceResolve")) {
            resolverOcorrencia(request, response);
            return;
        }
        ocorrencia(request, response);

    }

    protected void ocorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Ocorrencia> lista = OcorrenciaDao.listar();
        request.setAttribute("ocorrencias", lista);
        request.getParameter("validator");
        RequestDispatcher rd = request.getRequestDispatcher("registroOcorrencias.jsp");
        rd.forward(request, response);
    }


    protected void registrarOcorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginDao loginDao = LoginDao.getInstance();
        String titulo = request.getParameter("titulo");
        String texto = request.getParameter("texto");
        int id_morador = Integer.parseInt(loginDao.recuperarId(loginDao.recuperarCpf(request)));
        var ocorrencia = new Ocorrencia(titulo, texto, "", id_morador);


        if (OcorrenciaDao.registrar(ocorrencia)) {
            request.setAttribute("validator", true);
            System.out.println("Ocorrência registrada com sucesso.");
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("Ocorrencias.jsp");

        } else {
            request.getSession().setAttribute("validator", false);
            System.out.println("Erro ao registrar ocorrência.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    protected void listarOcorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ocorrencias.setId(Integer.parseInt(request.getParameter("id")));
        OcorrenciaDao.selecionar(ocorrencias);
        request.setAttribute("id", ocorrencias.getId());
        request.setAttribute("titulo", ocorrencias.getTitulo());
        request.setAttribute("texto", ocorrencias.getTexto());
        request.setAttribute("resolucao", ocorrencias.getResolucao());
        RequestDispatcher rd = request.getRequestDispatcher(".jsp");
        rd.forward(request, response);
        response.sendRedirect("");
    }

    protected void removerOcorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ocorrencias.setId(Integer.parseInt(request.getParameter("id")));
        if (OcorrenciaDao.deletar(ocorrencias)) {
            request.getSession().setAttribute("validador", true);
        } else
            request.getSession().setAttribute("validador", false);
        response.sendRedirect("");
    }

    private void editarOcorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ocorrencias.setId(Integer.parseInt(request.getParameter("id")));
        ocorrencias.setTitulo(request.getParameter("titulo"));
        ocorrencias.setTexto(request.getParameter("texto"));
        ocorrencias.setResolucao(request.getParameter("resolucao"));
        if (OcorrenciaDao.editar(ocorrencias)) {
            request.getSession().setAttribute("validador", true);
        } else
            request.getSession().setAttribute("validador", false);
        response.sendRedirect("");
    }

    private void resolverOcorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ocorrencias.setId(Integer.parseInt(request.getParameter("id")));
        ocorrencias.setResolucao(request.getParameter("resolucao"));
        if (OcorrenciaDao.resolver(ocorrencias)) {
            request.getSession().setAttribute("validador", true);
        } else
            request.getSession().setAttribute("validador", false);
        response.sendRedirect("");
    }

}
