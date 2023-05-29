package br.com.residup.servlets;

import br.com.residup.daos.MoradorDao;
import br.com.residup.daos.OcorrenciaDao;
import br.com.residup.daos.ReservaDao;
import br.com.residup.daos.VisitanteDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/listarResumo"})
public class Resumo extends HttpServlet {

    ReservaDao reservaDao = ReservaDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getServletPath();
        var visitantedao = VisitanteDao.getInstance();
        try {
            if(action.equals("/listarResumo") || action.equals("/")){

                String stringIdMoardor = (String) request.getSession().getAttribute("id_morador");
                int idMotador = Integer.parseInt(stringIdMoardor);

                List minhasReservas = reservaDao.reservas(idMotador);
                request.setAttribute("minhasReservas", minhasReservas);

                List minhasOcorrencias = OcorrenciaDao.listarDoMorador(idMotador);
                request.setAttribute("minhasOcorrencias", minhasOcorrencias);

                List meusVisitantes = visitantedao.listarVisitantes(idMotador,"");
                request.setAttribute("meusVisitantes", meusVisitantes);

                request.getRequestDispatcher("resumo.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
