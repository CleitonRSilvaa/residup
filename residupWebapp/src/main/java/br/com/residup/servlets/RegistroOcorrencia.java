package br.com.residup.servlets;

import br.com.residup.daos.OcorrenciaDao;
import br.com.residup.models.IconAlertJS;
import br.com.residup.models.Ocorrencia;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static br.com.residup.shared.Uteis.scriptMensagemAlertJs;

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

    protected void ocorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_morador = String.valueOf(request.getSession().getAttribute("id_morador"));
        String filtroOcorrencias = request.getParameter("form-status");
        ArrayList<Ocorrencia> lista = OcorrenciaDao.listarDoMorador(Integer.parseInt(id_morador));
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
        RequestDispatcher rd = request.getRequestDispatcher("ocorrencia.jsp");
        response.setStatus(HttpServletResponse.SC_OK);
        rd.forward(request, response);
    }


    protected void registrarOcorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String texto = request.getParameter("texto");
        String id_morador = String.valueOf(request.getSession().getAttribute("id_morador"));

        if (titulo.length() > 20) {
            String msg = "Título limitado a 20 caracteres.";
            request.getSession().setAttribute("mgsJS", scriptMensagemAlertJs(IconAlertJS.error, "Erro ao registrar ocorrência!", msg));
            request.getSession().setAttribute("validator", true);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("/Ocorrencia");
            return;
        }
        var ocorrencia = Ocorrencia.builder().titulo(titulo).texto(texto).id_morador(Integer.parseInt(id_morador)).build();
        if (OcorrenciaDao.registrar(ocorrencia)) {
            String msgJs = scriptMensagemAlertJs(IconAlertJS.success, "Ocorrência registrada com sucesso!", "Aguarde o retorno do síndico.");
            request.getSession().setAttribute("mgsJS", msgJs);
            request.getSession().setAttribute("validator", true);
            System.out.println("Ocorrência registrada com sucesso.");
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/Ocorrencia");
            return;
        }
    }


    protected void removerOcorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ocorrencias.setId_ocorrencia(Integer.parseInt(request.getParameter("id")));
        if (OcorrenciaDao.deletar(ocorrencias)) {
            request.getSession().setAttribute("validador", true);
            response.setStatus(HttpServletResponse.SC_OK);
        } else
            request.getSession().setAttribute("validador", false);
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.sendRedirect("/Ocorrencia");
    }
}
