package br.com.residup.servlets;

import br.com.residup.daos.IconAlertJS;
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
        ArrayList<Ocorrencia> lista = OcorrenciaDao.listarDoMorador(Integer.parseInt((id_morador)));

        Boolean parametro = (Boolean) request.getSession().getAttribute("validator");
        String mgs = (String) request.getSession().getAttribute("mgsJS");

        System.out.println(mgs);
        System.out.println(parametro);


        if (parametro != null) {
            if (parametro) {
                String msg = mgs;
                request.setAttribute("mensagem", msg);
            }
        }
        request.getSession().removeAttribute("validator");
        request.getSession().removeAttribute("mgsJS");
        request.setAttribute("ocorrencias", lista);
        RequestDispatcher rd = request.getRequestDispatcher("ocorrencia.jsp");
        rd.forward(request, response);
    }


    protected void registrarOcorrencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String texto = request.getParameter("texto");
        String id_morador = String.valueOf(request.getSession().getAttribute("id_morador"));
        var ocorrencia = new Ocorrencia(titulo, texto, "", Integer.parseInt((id_morador)));


        if (OcorrenciaDao.registrar(ocorrencia)) {
            String msgJs = scriptMensagemAlertJs(IconAlertJS.success, "Ocorrência registrada com sucesso!", "Aguarde o retorno do síndico.");
            request.getSession().setAttribute("mgsJS", msgJs);
            request.getSession().setAttribute("validator", true );
            System.out.println("Ocorrência registrada com sucesso.");
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/Ocorrencia");



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
//        RequestDispatcher rd = request.getRequestDispatcher("ocorrencia.jsp");
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

    public static String scriptMensagemAlertJs(IconAlertJS iconAlertJS, String titulo, String messagem) {
        String mgs = "Swal.fire(\n '" + titulo + "',\n'" + messagem + "'\n,'" + iconAlertJS + "'\n" + ");\n";
        return mgs;
    }

}
