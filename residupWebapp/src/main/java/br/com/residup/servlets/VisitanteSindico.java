package br.com.residup.servlets;

import br.com.residup.daos.VisitanteDao;
import br.com.residup.models.IconAlertJS;
import br.com.residup.models.Visitante;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static br.com.residup.shared.Uteis.scriptMensagemAlertJs;


@WebServlet(urlPatterns = {"/visitantesAdm", "/CheckInVisitantes"})
public class VisitanteSindico extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            var visitantedao = VisitanteDao.getInstance();
            String filtro = request.getParameter("dataFiltro");
            List lista = visitantedao.allVisitantes(filtro);

            Boolean resultVisitante = (Boolean) request.getSession().getAttribute("resultVisitante");
            String mgs = (String) request.getSession().getAttribute("mgsJS");
            if (resultVisitante != null) {
                if (resultVisitante) {
                    String msg = mgs;
                    request.setAttribute("mensagem", msg);
                }
            }
            request.getSession().removeAttribute("resultVisitante");
            request.getSession().removeAttribute("mgsJS");
            request.setAttribute("listaVisitantes", lista);
            request.getRequestDispatcher("visitantesSindico.jsp").forward(request, response);
        }catch (Exception e){
            List<Visitante> lista = new ArrayList();
            request.setAttribute("listaVisitantes", lista);
            String msgJs = scriptMensagemAlertJs(IconAlertJS.error, "Atenção", "Ocorreu um erro inesperado tente novamente mais tarde!");
            request.setAttribute("mensagem", msgJs);
            request.getRequestDispatcher("visitantesSindico.jsp").forward(request, response);
        }


    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var visitantedao = VisitanteDao.getInstance();
        String idRegistro = request.getParameter("idReserva");

        if (visitantedao.fazerCheckInVisitante(Integer.parseInt(idRegistro))){

            String msgJs = scriptMensagemAlertJs(IconAlertJS.success, "Sucesso", "Check-In visitante feita com sucesso!");
            request.getSession().setAttribute("mgsJS", msgJs);
            request.getSession().setAttribute("resultVisitante", true);

        }else {
            String msgJsErro = scriptMensagemAlertJs(IconAlertJS.error, "Erro", "Erro ao dar Check-In no visitante!");
            request.getSession().setAttribute("mgsJS", msgJsErro);
            request.getSession().setAttribute("resultVisitante", true);
        }
        response.sendRedirect("visitantesAdm");

    }
}
