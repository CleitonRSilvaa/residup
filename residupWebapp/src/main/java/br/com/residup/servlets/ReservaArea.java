package br.com.residup.servlets;

import br.com.residup.models.Convidado;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.com.residup.shared.Uteis.scriptMensagemAlertJs;

@WebServlet(urlPatterns = {"/reservas", "/insertReserva", "/selectReserva", "/updateRerva", "/deleteReserva" , "/excluirConvidado", "/cadastroConvidado","/convidosReserva"})
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


            ReservaDao reservaDao = ReservaDao.getInstance();
            String modal = (String) request.getSession().getAttribute("mgsmodal");
            String id_reserva = (String) request.getSession().getAttribute("idReserva");
            if (modal != null && id_reserva != null  ) {
                request.setAttribute("mgsmodal", modal);
                List convidadosList = reservaDao.convidados(1, Integer.parseInt(id_reserva));
                List convidados = new ArrayList();
                for (Object convidado : convidadosList) {
                    Convidado convidado1 = (Convidado) convidado;
                    convidados.add(convidado1);
                }
                System.out.println("ID DA RESERVA SELESIONADA "+id_reserva);
//                request.setAttribute("IdReservaConvidado", id_reserva);
                request.setAttribute("listaConvidados", convidados);

            }

            request.getSession().removeAttribute("idReserva");
            request.getSession().removeAttribute("resultReserva");
            request.getSession().removeAttribute("mgsJS");
            request.getSession().removeAttribute("mgsmodal");

            List reservaList = reservaDao.reservas(1);
            List areasList = reservaDao.areas();

            request.setAttribute("revervas", reservaList);
            request.setAttribute("areas", areasList);
            request.getRequestDispatcher("/Telas/Reservamorador.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/insertReserva")) {
            gravarReserva(request, response);
            return;
        }
        if (action.equals("/convidosReserva")) {
            try {
                convidados(request, response);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (action.equals("/deleteReserva")) {
            doDelete(request,response);
        }

        if(action.equals("/cadastroConvidado")){
            cadastroConvidado(request,response);
            return;
        }

        if (action.equals("/excluirConvidado")) {
            try {
                excuirConvidados(request, response);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void deletarReserva(HttpServletRequest request, HttpServletResponse response){

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String action = request.getServletPath();
            System.out.println(action);
            String idReserva = (String) request.getSession().getAttribute("idReservaDelete");
            String idReserva2 = request.getParameter("idReservaDelete");
            ReservaDao reservaDao = ReservaDao.getInstance();
            var reserva = Reserva.builder().idReserva(Integer.parseInt(idReserva)).idMorador(2).build();
            System.out.println(reserva);
            System.out.println(idReserva2);
            if (reservaDao.deleteReserva(reserva)) {
                String msgJs = scriptMensagemAlertJs(IconAlertJS.success, "Sucesso", "Reserva cancelada com sucesso!");
                request.getSession().setAttribute("mgsJS", msgJs);
                request.getSession().setAttribute("resultReserva", true);
                response.sendRedirect("/reservas");
                return;
            }

            String msgJs =  scriptMensagemAlertJs(IconAlertJS.error,"Opsss","Erro ao cancelar reserva!");
            request.getSession().setAttribute("mgsJS", msgJs);
            request.getSession().setAttribute("inserReserva",true );
            response.sendRedirect("/reservas");

        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("/reservas");
        }

    }

    public void gravarReserva(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            String data = request.getParameter("dataInput");
            String idArea = request.getParameter("areaSelect");
            String hora = request.getParameter("horarioSelect");
            ReservaDao reservaDao = ReservaDao.getInstance();
            String cpf = (String) request.getSession().getAttribute("cpf");
            String id_moardor = (String) request.getSession().getAttribute("id_morador");
            System.out.println("CPF: "+ cpf + " ID : "+id_moardor);
            var reserva = Reserva.builder().dateReserva(data).horaReserva(hora).idMorador(Integer.parseInt(id_moardor)).build();

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
                String msgJs = scriptMensagemAlertJs(IconAlertJS.warning, "Atenção", "Você pode reservar uma area por dia !");
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
            String msgJs = scriptMensagemAlertJs(IconAlertJS.error, "Atenção", "Houve um erro ao inesperado \n tente novamente mais tarde");
            request.getSession().setAttribute("mgsJS", msgJs);
            request.getSession().setAttribute("resultReserva", true);
            System.out.println(e);
            response.sendRedirect("/reservas");
        }
    }


    public void convidados(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String action = request.getServletPath();
        if (action.equals("/convidosReserva")) {
            String idReserva = request.getParameter("idReserva");
            request.getSession().setAttribute("idReserva",idReserva);
            request.getSession().setAttribute("mgsmodal","ok" );
            response.sendRedirect("/reservas");
        }

    }


    public void excuirConvidados(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ParseException {
        String idReserva = request.getParameter("idReservaListaConvidadoExclui");
        String id_convidado = request.getParameter("idConviado");

        ReservaDao reservaDao = ReservaDao.getInstance();
        var reserva  = reservaDao.reserva(Integer.parseInt(idReserva));

        if (reserva.getDateReserva() != null){
            Date today = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(reserva.getDateReserva());
            if (date.compareTo(today) <= 0){
                String msgJs = scriptMensagemAlertJs(IconAlertJS.info, "Atenção", "Convidado não pode ser excluido! data do Evento vencida ");
                request.getSession().setAttribute("mgsJS", msgJs);
                request.getSession().setAttribute("resultReserva", true);
                response.sendRedirect("/reservas");
                return;
            }
            if (!reservaDao.deleteConviado(Integer.parseInt(id_convidado))){
                String msgJs = scriptMensagemAlertJs(IconAlertJS.info, "Opss", "Convidado não pode ser excluido!");
                request.getSession().setAttribute("mgsJS", msgJs);
                request.getSession().setAttribute("resultReserva", true);
                response.sendRedirect("/reservas");
                return;
            }

        }
        response.sendRedirect("/reservas");

    }




    public void cadastroConvidado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idReserva = request.getParameter("idReservaListaConvidado");
        String nomeConvidado = request.getParameter("nomeConvidado");
        String identidade =  request.getParameter("identidade");
        String id_moardor = (String) request.getSession().getAttribute("id_morador");
        request.getParameter("idReserva");
        System.out.println(idReserva);
        ReservaDao reservaDao = ReservaDao.getInstance();
        var convidado = new Convidado(0,nomeConvidado,identidade,Integer.parseInt(id_moardor),Integer.parseInt(idReserva));
        var reserva  = reservaDao.insertConviado(convidado);
        request.getSession().setAttribute("idReserva",idReserva);
        response.sendRedirect("/reservas");



    }



    public String validarCapos(Reserva reserva) {
        if (reserva.getDateReserva().trim().isBlank() || reserva.getDateReserva().trim().isEmpty())
            return "Date is NULL";
        if (reserva.getHoraReserva().trim().isBlank() || reserva.getHoraReserva().trim().isEmpty())
            return "Hora is NULL";
        if (reserva.getIdArea() == -1) return "ID_AREA is NULL";
        return "ok";
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
