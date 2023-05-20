package br.com.residup.servlets;

import br.com.residup.models.IconAlertJS;
import br.com.residup.daos.ReservaDao;
import br.com.residup.models.Reserva;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@WebServlet(urlPatterns = {"/reservas", "/insertReserva", "/selectReserva", "/updateRerva", "/deleteReserva"})
public class ReservaArea extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String action = request.getServletPath();

            if (action.equals("/deleteReserva")) {
                doDelete(request, response);
                return;
            }

            Boolean parametro3 = (Boolean) request.getSession().getAttribute("resultReserva");
            String mgs = (String) request.getSession().getAttribute("mgsJS");
            if (parametro3 != null) {
                if (parametro3) {
                    String msg = mgs;
                    request.setAttribute("mensagem", msg);
                }
            }


            request.getSession().removeAttribute("resultReserva");
            request.getSession().removeAttribute("mgsJS");

            ReservaDao reservaDao = ReservaDao.getInstance();
            List reservaList = reservaDao.reservas(2);
            List areasList = reservaDao.areas();

            request.setAttribute("revervas", reservaList);
            request.setAttribute("areas", areasList);
            request.getRequestDispatcher("reservaArea.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/insertReserva")) {
            try {

                String data = request.getParameter("dataInput");
                String idArea = request.getParameter("areaSelect");
                String hora = request.getParameter("horarioSelect");
                ReservaDao reservaDao = ReservaDao.getInstance();

                var reserva = Reserva.builder().dateReserva(data).horaReserva(hora).idMorador(2).build();

                if (idArea.trim().isEmpty()) {
                    reserva.setIdArea(-1);
                } else reserva.setIdArea(Integer.parseInt(idArea));


                if (!validarCapos(reserva).equals("ok")) {
                    String msgJs = scriptMensagemAlertJs(IconAlertJS.warning, "Atenção", "Todos os campos devem estar preenchidos");
                    request.getSession().setAttribute("mgsJS", msgJs);
                    request.getSession().setAttribute("resultReserva", true);
                    response.sendRedirect("/reservas");
                    return;
                }


                if (!comparaData(data)) {
                    String msgJs = scriptMensagemAlertJs(IconAlertJS.warning, "Atenção", "Informe uma data maior que a data de hoje ");
                    request.getSession().setAttribute("mgsJS", msgJs);
                    request.getSession().setAttribute("resultReserva", true);
                    response.sendRedirect("/reservas");
                    return;
                }

                if (reservaDao.moradorTemRederva(reserva)) {
                    String msgJs = scriptMensagemAlertJs(IconAlertJS.warning, "Atenção", "Você só pode reservar uma area por dia !");
                    request.getSession().setAttribute("mgsJS", msgJs);
                    request.getSession().setAttribute("resultReserva", true);
                    response.sendRedirect("/reservas");
                    return;
                }
                if (reservaDao.TemReserva(reserva)) {
                    String msgJs = scriptMensagemAlertJs(IconAlertJS.warning, "Atenção", "Infelizmente ja tem uma reserva para este dia, horário e area!");
                    request.getSession().setAttribute("mgsJS", msgJs);
                    request.getSession().setAttribute("resultReserva", true);
                    response.sendRedirect("/reservas");
                    return;
                }

                if (reservaDao.insertReserva(reserva)) {
                    String msgJs = scriptMensagemAlertJs(IconAlertJS.success, "Sucesso", "Reserva realizada com sucesso!");
                    request.getSession().setAttribute("mgsJS", msgJs);
                    request.getSession().setAttribute("resultReserva", true);
                    response.sendRedirect("/reservas");
                    return;
                }

                response.sendRedirect("/reservas");
            } catch (Exception e) {
                System.out.println(e);
                response.sendRedirect("/reservas");
            }


        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            if (action.equals("/deleteReserva")) {
                String idReserva = request.getParameter("idReserva");
                ReservaDao reservaDao = ReservaDao.getInstance();
                var reserva = Reserva.builder().idReserva(Integer.parseInt(idReserva)).idMorador(2).build();

                if (reservaDao.deleteReserva(reserva)) {
                    String msgJs = scriptMensagemAlertJs(IconAlertJS.success, "Sucesso", "Reserva cancelada com sucesso!");
                    request.getSession().setAttribute("mgsJS", msgJs);
                    request.getSession().setAttribute("resultReserva", true);
                    response.sendRedirect("/reservas");
                    return;
                }

//            String msgJs =  scriptMensagemAlertJs(IconAlertJS.error,"Opsss","Erro ao cancelar reserva!");
//            request.getSession().setAttribute("mgsJS", msgJs);
//            request.getSession().setAttribute("inserReserva", );
                response.sendRedirect("/reservas");
            }
        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("/reservas");
        }

    }


    public String validarCapos(Reserva reserva) {
        if (reserva.getDateReserva().trim().isBlank() || reserva.getDateReserva().trim().isEmpty())
            return "Date is NULL";
        if (reserva.getHoraReserva().trim().isBlank() || reserva.getHoraReserva().trim().isEmpty())
            return "Hora is NULL";
        if (reserva.getIdArea() == -1) return "ID_AREA is NULL";
        return "ok";
    }

    public static String scriptMensagemAlertJs(IconAlertJS iconAlertJS, String titulo, String messagem) {
        String mgs = "Swal.fire(\n '" + titulo + "',\n'" + messagem + "'\n,'" + iconAlertJS + "'\n" + ");\n";
        return mgs;
    }

    public static boolean comparaData(String dateString) {
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateString);
            return date.compareTo(today) > 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
