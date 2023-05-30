package br.com.residup.servlets;

import br.com.residup.daos.MoradorDao;
import br.com.residup.daos.ReservaDao;
import br.com.residup.models.Convidado;
import br.com.residup.models.IconAlertJS;
import br.com.residup.models.Morador;
import br.com.residup.models.Reserva;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


import static br.com.residup.shared.Uteis.scriptMensagemAlertJs;
import static org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent;

@WebServlet(urlPatterns = {"/reservas", "/insertReserva", "/selectReserva", "/updateRerva", "/deleteReserva", "/excluirConvidado", "/cadastroConvidado", "/convidosReserva" ,"/updateMoradorTelaReserva" })
public class ReservaArea extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getServletPath();
          if(action.equals("/reservas")) {
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
              String id_moardor = (String) request.getSession().getAttribute("id_morador");
              List reservaList = reservaDao.reservas(Integer.parseInt(id_moardor));
              List areasList = reservaDao.areas();
              String cpf = (String) request.getSession().getAttribute("cpf");
              Morador morador = new MoradorDao().buscarMorador(cpf);

              request.setAttribute("morador", morador);
              request.setAttribute("revervas", reservaList);
              request.setAttribute("areas", areasList);
              request.getRequestDispatcher("Reservamorador.jsp").forward(request, response);
          }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        request.setCharacterEncoding("UTF-8");

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
            doDelete(request, response);
        }

        if (action.equals("/cadastroConvidado")) {
            cadastroConvidado(request, response);
            return;
        }
        if (action.equals("/updateMoradorTelaReserva")) {

            updatePerfilMorador(request,response);

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


    public void deletarReserva(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String idReserva = request.getParameter("idReservaDelete");
            ReservaDao reservaDao = ReservaDao.getInstance();
            String id_moardor = (String) request.getSession().getAttribute("id_morador");
            var reserva = Reserva.builder().idReserva(Integer.parseInt(idReserva)).idMorador(Integer.parseInt(id_moardor)).build();
            boolean deleteAllConvidados = true;
            if (reservaDao.temConvidado(Integer.parseInt(idReserva))){
                 deleteAllConvidados = reservaDao.deleteConvidadosEventos(Integer.parseInt(idReserva));
            }

            boolean deleteReserva = reservaDao.deleteReserva(reserva);

            if (deleteAllConvidados && deleteReserva) {
                String msgJs = scriptMensagemAlertJs(IconAlertJS.success, "Sucesso", "Reserva cancelada com sucesso!");
                request.getSession().setAttribute("mgsJS", msgJs);
                request.getSession().setAttribute("resultReserva", true);
                response.sendRedirect("/reservas");
                return;
            }

            String msgJs = scriptMensagemAlertJs(IconAlertJS.error, "Opsss", "Erro ao cancelar reserva!");
            request.getSession().setAttribute("mgsJS", msgJs);
            request.getSession().setAttribute("inserReserva", true);
            response.sendRedirect("/reservas");

        } catch (Exception e) {
            System.out.println(e);
            String msgJs = scriptMensagemAlertJs(IconAlertJS.error, "Opsss", "Ouve um erro inesperado tente novamente mais tarde!");
            request.getSession().setAttribute("mgsJS", msgJs);
            request.getSession().setAttribute("inserReserva", true);
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
            System.out.println("CPF: " + cpf + " ID : " + id_moardor);
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
                String msgJs = scriptMensagemAlertJs(IconAlertJS.warning, "Atenção", "Informe uma data maior que a data de hoje ou faça uma reserva antes do meio-dia  ");
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
            String msgJs = scriptMensagemAlertJs(IconAlertJS.error, "Atenção", "Houve um erro ao inesperado  tente novamente mais tarde");
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
            request.getSession().setAttribute("idReserva", idReserva);
            request.getSession().setAttribute("mgsmodal", "ok");
            response.sendRedirect("/reservas");
        }

    }


    public void excuirConvidados(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ParseException {
        String idReserva = request.getParameter("idReservaListaConvidadoExclui");
        String id_convidado = request.getParameter("idConviado");

        ReservaDao reservaDao = ReservaDao.getInstance();
        var reserva = reservaDao.reserva(Integer.parseInt(idReserva));

        if (reserva.getDateReserva() != null) {
            Date today = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(reserva.getDateReserva());
            LocalDate dataAtual = LocalDate.now();
            LocalDate dataConvertida = LocalDate.parse(reserva.getDateReserva());
            if (dataConvertida.isBefore(dataAtual)) {
                String msgJs = scriptMensagemAlertJs(IconAlertJS.info, "Atenção", "Convidado não pode ser excluido! data do Evento vencida ");
                request.getSession().setAttribute("mgsJS", msgJs);
                request.getSession().setAttribute("resultReserva", true);
                response.sendRedirect("/reservas");
                return;
            }
            if (!reservaDao.deleteConvidado(Integer.parseInt(id_convidado))) {
                String msgJs = scriptMensagemAlertJs(IconAlertJS.info, "Opss", "Convidado não pode ser excluido!");
                request.getSession().setAttribute("mgsJS", msgJs);
                request.getSession().setAttribute("resultReserva", true);
                response.sendRedirect("/reservas");
                return;
            }

        }
        request.getSession().setAttribute("idReserva", idReserva);
        request.getSession().setAttribute("mgsmodal", "ok");
        response.sendRedirect("/reservas");

    }


    public void cadastroConvidado(HttpServletRequest request, HttpServletResponse response) {

        try {
            String idReservaNew = request.getParameter("idReservaListaConvidado");
            String nomeConvidado = request.getParameter("nomeConvidado");
            String identidade = request.getParameter("identidade");
            String id_moardor = (String) request.getSession().getAttribute("id_morador");
            request.getParameter("idReserva");
            ReservaDao reservaDao = ReservaDao.getInstance();
            var convidado = new Convidado(0, nomeConvidado, identidade, Integer.parseInt(id_moardor), Integer.parseInt(idReservaNew));
            var reserva = reservaDao.insertConviado(convidado);
            request.getSession().setAttribute("idReserva", idReservaNew);
            request.getSession().setAttribute("mgsmodal", "ok");
            response.sendRedirect("/reservas");
        } catch (Exception e) {

        }


    }

    public void updatePerfilMorador(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> parameters = uploadImage(request);
        String fotoMoradorImagePath = parameters.get("image");

        var senha = parameters.get("newsenha");

        String cpf = (String) request.getSession().getAttribute("cpf");
        var morador = new Morador(cpf,senha);
        morador.setEnderecoFoto(fotoMoradorImagePath);

//        if(!MoradorDao.updateMoradorPerfil(morador)){
//            String msgJs = scriptMensagemAlertJs(IconAlertJS.error, "Erro", "Ocorreu um erro na alteração do Perfil. Tente novamente mais tarde!");
//            request.getSession().setAttribute("mgsJS", msgJs);
//            request.getSession().setAttribute("resultReserva", true);
//            response.sendRedirect("/reservas");
//            return;
//        }
//        String msgJs = scriptMensagemAlertJs(IconAlertJS.success, "Sucesso", "Dados atualizado com sucesso");
//        request.getSession().setAttribute("mgsJS", msgJs);
//        request.getSession().setAttribute("resultReserva", true);
//        response.sendRedirect("/reservas");
    }
    private Map<String, String> uploadImage(HttpServletRequest httpServletRequest) {

        Map<String, String> requestParameters = new HashMap<>();

        if (isMultipartContent(httpServletRequest)) {

            try {

                DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

                List<FileItem> fileItems = new ServletFileUpload(diskFileItemFactory).parseRequest(httpServletRequest);

                for (FileItem fileItem : fileItems) {

                    checkFieldType(fileItem, requestParameters);

                }

            } catch (Exception ex) {

                requestParameters.put("image", "imagens/fotoMorador/default-perfil.jpg");

            }

        }

        return requestParameters;

    }

    private void checkFieldType(FileItem item, Map requestParameters) throws Exception {

        if (item.isFormField()) {

            requestParameters.put(item.getFieldName(), item.getString());

        } else {

            String fileName = processUploadedFile(item);
            requestParameters.put("image", "imagens/fotoMorador/".concat(fileName));

        }

    }

    private String processUploadedFile(FileItem fileItem) throws Exception {
        Long currentTime = new Date().getTime();
        String fileName = currentTime.toString().concat("-").concat(fileItem.getName().replace(" ", ""));
        String filePath = this.getServletContext().getRealPath("imagens/fotoMorador").concat(File.separator).concat(fileName);
        fileItem.write(new File(filePath));
        return fileName;
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

        try {

            LocalDateTime dataHora = LocalDateTime.now();
            LocalDate dataAtual = LocalDate.now();
            LocalDate dataConvertida = LocalDate.parse(dateString);

            boolean horaMaiorQueMeioDia = (dataHora.getHour() > 12);
            if(dataConvertida.isAfter(dataAtual)) return  true;
            if (dataConvertida.isEqual(dataAtual) ) {
                System.out.println("A data fornecida é igual ou maior que o dia de hoje.");
                if (horaMaiorQueMeioDia) return false;
                return true;
            }

            return  false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
