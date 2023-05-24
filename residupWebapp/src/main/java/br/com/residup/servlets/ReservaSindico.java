package br.com.residup.servlets;

import br.com.residup.daos.ReservaDao;
import br.com.residup.models.Convidado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/reservasAdmin"})
public class ReservaSindico extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            if (action.equals("/reservas")) {
                Boolean parametro3 = (Boolean) request.getSession().getAttribute("resultReserva");
                String mgs = (String) request.getSession().getAttribute("mgsJS");
                if (parametro3 != null) {
                    if (parametro3) {
                        String msg = mgs;
                        request.setAttribute("mensagem", msg);
                    }
                }

                ReservaDao reservaDao = ReservaDao.getInstance();
                String modal = (String) request.getSession().getAttribute("mgsmodal");
                String id_reserva = (String) request.getSession().getAttribute("idReserva");
                if (modal != null && id_reserva != null) {
                    String sgtIdMoardor = (String) request.getSession().getAttribute("id_morador");
                    request.setAttribute("mgsmodal", modal);
                    List convidadosList = reservaDao.convidados(Integer.parseInt(sgtIdMoardor), Integer.parseInt(id_reserva));
                    List convidados = new ArrayList();
                    for (Object convidado : convidadosList) {
                        Convidado convidado1 = (Convidado) convidado;
                        convidados.add(convidado1);
                    }
                    request.setAttribute("IdReservaConvidado", id_reserva);
                    request.setAttribute("listaConvidados", convidados);

                }

                request.getSession().removeAttribute("idReserva");
                request.getSession().removeAttribute("resultReserva");
                request.getSession().removeAttribute("mgsJS");
                request.getSession().removeAttribute("mgsmodal");
                String id_morador = (String) request.getSession().getAttribute("id_morador");
                List reservaList = reservaDao.reservas(Integer.parseInt(id_morador));
                List areasList = reservaDao.areas();

                request.setAttribute("reservas", reservaList);
                request.setAttribute("areas", areasList);
                request.getRequestDispatcher("Reservamorador.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
