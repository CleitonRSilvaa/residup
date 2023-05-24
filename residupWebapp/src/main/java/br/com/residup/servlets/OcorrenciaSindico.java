package br.com.residup.servlets;

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

@WebServlet(urlPatterns = {"/OcorrenciaAdm", "/carregarOcorrencia", "/resolver"})
public class OcorrenciaSindico extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Ocorrencia ocorrencias = new Ocorrencia();

    public OcorrenciaSindico(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/OcorrenciaAdm")) {
            ocorrencia(request, response);
            return;
        }
        if (action.equals("/carregarOcorrencia")) {
            carregarOcorrencia(request, response);
            return;
        }
    }
    protected void ocorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filtroOcorrencias = request.getParameter("status-filter");
        ArrayList<Ocorrencia> lista = OcorrenciaDao.listarDoMoradorSindico();
        ArrayList<Ocorrencia> listaFiltrada = new ArrayList<>();

        if (filtroOcorrencias != null && !filtroOcorrencias.isEmpty() && !filtroOcorrencias.equals("todos")) {
            for (Ocorrencia ocorrencia : lista) {
                if (ocorrencia.getStatus().equals(filtroOcorrencias)) {
                    listaFiltrada.add(ocorrencia);
                }
            }
        } else {
            listaFiltrada = lista;
            filtroOcorrencias = "todos";
        }

        Boolean parametro = (Boolean) request.getSession().getAttribute("validator");
        String mgs = (String) request.getSession().getAttribute("mgsJS");

        if (parametro != null && parametro) {
            String msg = mgs;
            request.setAttribute("mensagem", msg);
        }

        request.getSession().removeAttribute("validator");
        request.getSession().removeAttribute("mgsJS");
        request.setAttribute("ocorrencias", listaFiltrada);
        request.setAttribute("filtroOcorrencias", filtroOcorrencias);
        RequestDispatcher rd = request.getRequestDispatcher("ocorrenciaSindico.jsp");
        response.setStatus(HttpServletResponse.SC_OK);
        rd.forward(request, response);
    }

    private void carregarOcorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idOcorrenciaSindico = request.getParameter("idOcorrenciaSindico");

        var ocorrencia = OcorrenciaDao.getOcorrencia(Integer.parseInt(idOcorrenciaSindico));
        request.setAttribute("ocorrenciaUnica", ocorrencia);
        RequestDispatcher rd = request.getRequestDispatcher("Telas/ocorrenciadetalhada.jsp");
        response.setStatus(HttpServletResponse.SC_OK);
        rd.forward(request, response);


    }



    public void resolverOcorrencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //        if (OcorrenciaDao.resolver(ocorrencias)) {
//            request.getSession().setAttribute("validador", true);
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else
//            request.getSession().setAttribute("validador", false);
//        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        response.sendRedirect("/OcorrenciaAdm");
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/resolver")) {
            carregarOcorrencia(request, response);
            return;
        }
    }
}