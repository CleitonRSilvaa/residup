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
import java.util.List;

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
        if (action.equals("/Ocorrencia")) {
            request.getSession().setAttribute("validator", true);
            ocorrencia(request, response);
            return;
        }

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
        int id_morador = (int) request.getSession().getAttribute("id_morador");
        ArrayList<Ocorrencia> lista = OcorrenciaDao.listarDoMorador(id_morador);
        request.setAttribute("ocorrencias", lista);
        request.getParameter("validator");
        RequestDispatcher rd = request.getRequestDispatcher("Ocorrencias.jsp");
        rd.forward(request, response);
    }


    protected void registrarOcorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginDao loginDao = LoginDao.getInstance();
        String titulo = request.getParameter("titulo");
        String texto = request.getParameter("texto");
        int id_morador = (int) request.getSession().getAttribute("id_morador");
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

//    protected void listarOcorrencia(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        int idmorador = ;
//
//
//        List<Ocorrencia> listaOcorrencias = OcorrenciaDao.selecionar(idmorador);
//        System.out.println("Tamanho da lista de ocorrencias: " + listaOcorrencias.size());
//
//        request.setAttribute("ocorrencias", listaOcorrencias);
//        RequestDispatcher rd = request.getRequestDispatcher("Ocorrencias.jsp");
//        rd.forward(request, response);
//    }




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
        ocorrencias.setStatus(request.getParameter("resolucao"));
        if (OcorrenciaDao.editar(ocorrencias)) {
            request.getSession().setAttribute("validador", true);
        } else
            request.getSession().setAttribute("validador", false);
        response.sendRedirect("");
    }

    private void resolverOcorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ocorrencias.setId(Integer.parseInt(request.getParameter("id")));
        ocorrencias.setStatus(request.getParameter("resolucao"));
        if (OcorrenciaDao.resolver(ocorrencias)) {
            request.getSession().setAttribute("validador", true);
        } else
            request.getSession().setAttribute("validador", false);
        response.sendRedirect("");
    }

}
