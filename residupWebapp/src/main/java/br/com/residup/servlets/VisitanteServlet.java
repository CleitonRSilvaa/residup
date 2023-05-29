package br.com.residup.servlets;

import java.io.Serial;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.residup.models.IconAlertJS;
import br.com.residup.models.Reserva;
import br.com.residup.models.Visitante;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import br.com.residup.daos.VisitanteDao;

import static br.com.residup.shared.Uteis.scriptMensagemAlertJs;


@WebServlet(urlPatterns = {"/visitantes", "/insert", "/select", "/update", "/delete", "/report"})
public class VisitanteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            if (action.equals("/visitantes")) {
                visitantes(request, response);
                return;
            }

            if (action.equals("/delete")) {
                removerVisitante(request, response);
                return;
            }
            if (action.equals("/report")) {
                gerarRelatorio(request, response);
                return;
            }
            visitantes(request, response);
        } catch (Exception e){
            String msg = scriptMensagemAlertJs(IconAlertJS.warning, "Atenção", "Ocorreu um erro indesperado tente novamente mais tarde !");
            request.setAttribute("mensagem", msg);
            RequestDispatcher rd = request.getRequestDispatcher("visitantes.jsp");
            rd.forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            if (action.equals("/insert")) {
                adicionarVisitante(request, response);
                return;
            }
            if (action.equals("/select")) {
                buscarVisitante(request, response);
                return;
            }
            if (action.equals("/update")) {
                editarVisitante(request, response);
            }
        }catch (Exception e){
            String msg = scriptMensagemAlertJs(IconAlertJS.warning, "Atenção", "Ocorreu um erro indesperado tente novamente mais tarde !");
            request.setAttribute("mensagem", msg);
            RequestDispatcher rd = request.getRequestDispatcher("visitantes.jsp");
            rd.forward(request, response);
        }

    }



    protected void visitantes(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String idMoardor = (String) request.getSession().getAttribute("id_morador");

        Boolean resultVisitante = (Boolean) request.getSession().getAttribute("resultVisitante");
        String mgs = (String) request.getSession().getAttribute("mgsJS");
        if (resultVisitante != null) {
            if (resultVisitante) {
                String msg = mgs;
                request.setAttribute("mensagem", msg);
            }
        }





        var visitantedao = VisitanteDao.getInstance();
        String filtro = request.getParameter("txtBsca");
        System.out.println(filtro);
        ArrayList<Visitante> lista = visitantedao.listarVisitantes(Integer.parseInt(idMoardor),filtro);
        request.setAttribute("listaVisitantes", lista);

        request.getSession().removeAttribute("resultVisitante");
        request.getSession().removeAttribute("mgsJS");

        RequestDispatcher rd = request.getRequestDispatcher("visitantes.jsp");
        rd.forward(request, response);
    }


    protected void adicionarVisitante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String documento = request.getParameter("documento");
        String fone = request.getParameter("fone");
        var visitante = new Visitante(nome,sobrenome,documento);
        visitante.setFone(fone);
        var visitantedao = VisitanteDao.getInstance();
        Boolean addVisitante  = visitantedao.inserirVisitante(visitante);
        String stingIdVisitante = visitantedao.getIdVisitante(documento);
        String idMoardor = (String) request.getSession().getAttribute("id_morador");
        Boolean addRegistroVisitante = visitantedao.inserirRegistroVisitante(Integer.parseInt(stingIdVisitante),Integer.parseInt(idMoardor));
        if (addVisitante && addRegistroVisitante){

            String msgJs = scriptMensagemAlertJs(IconAlertJS.success, "Sucesso", "Visitante cadastrado com sucesso!");
            request.getSession().setAttribute("mgsJS", msgJs);
            request.getSession().setAttribute("resultVisitante", true);

        }else {
            String msgJsErro = scriptMensagemAlertJs(IconAlertJS.error, "Erro", "Erro ao cadastrar visitante!");
            request.getSession().setAttribute("mgsJS", msgJsErro);
            request.getSession().setAttribute("resultVisitante", true);
        }
        response.sendRedirect("visitantes");

    }



    protected void buscarVisitante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        br.com.residup.models.Visitante visitante = new br.com.residup.models.Visitante();
        visitante.setId(request.getParameter("id"));
        var visitantedao = VisitanteDao.getInstance();
        visitantedao.selecionarVisitante(visitante);
        request.setAttribute("id", visitante.getId());
        request.setAttribute("nome", visitante.getNome());
        request.setAttribute("fone", visitante.getFone());
        request.setAttribute("email", visitante.getDocumento());
        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
        rd.forward(request, response);
    }


    protected void editarVisitante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        br.com.residup.models.Visitante visitante = new br.com.residup.models.Visitante();
        visitante.setId(request.getParameter("hiddenId"));
        visitante.setNome(request.getParameter("editedNome"));
        visitante.setSobrenome(request.getParameter("editedSobrenome"));
        visitante.setDocumento(request.getParameter("editedDocumento"));
        visitante.setFone(request.getParameter("editedFone"));
        var visitantedao = VisitanteDao.getInstance();
        if (visitantedao.alterarVisitante(visitante)){
            String msgJs = scriptMensagemAlertJs(IconAlertJS.success, "Sucesso", "Cadastro visitante atualizado com sucesso!");
            request.getSession().setAttribute("mgsJS", msgJs);
            request.getSession().setAttribute("resultVisitante", true);
        }else{
            String msgJsErro = scriptMensagemAlertJs(IconAlertJS.error, "Erro", "Erro ao atualizar cadastro do visitante!");
            request.getSession().setAttribute("mgsJS", msgJsErro);
            request.getSession().setAttribute("resultVisitante", true);
        }
        response.sendRedirect("visitantes");
    }

    protected void removerVisitante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        br.com.residup.models.Visitante visitante = new br.com.residup.models.Visitante();
        visitante.setId(request.getParameter("id"));
        var visitantedao = VisitanteDao.getInstance();
        if (visitantedao.deletarVisitante(visitante)){
            String msgJs = scriptMensagemAlertJs(IconAlertJS.success, "Sucesso", "Visitante deletado com sucesso!");
            request.getSession().setAttribute("mgsJS", msgJs);
            request.getSession().setAttribute("resultVisitante", true);
        }else{
            String msgJsErro = scriptMensagemAlertJs(IconAlertJS.error, "Erro", "Erro deletar visitante!");
        request.getSession().setAttribute("mgsJS", msgJsErro);
        request.getSession().setAttribute("resultVisitante", true);
        }
        response.sendRedirect("visitantes");
    }


    protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Document documento = new Document();
        try {
            response.setContentType("apllication/pdf");
            response.addHeader("Content-Disposition", "inline; filename=" + "Visitantes.pdf");
            PdfWriter.getInstance(documento, response.getOutputStream());
            documento.open();
            documento.add(new Paragraph("Lista de Visitantes:"));
            documento.add(new Paragraph(" "));
            PdfPTable tabela = new PdfPTable(3);
            PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
            PdfPCell col2 = new PdfPCell(new Paragraph("Documento"));
            PdfPCell col3 = new PdfPCell(new Paragraph("Fone"));
            tabela.addCell(col1);
            tabela.addCell(col2);
            tabela.addCell(col3);
            var visitantedao = VisitanteDao.getInstance();
            String idMoardor = (String) request.getSession().getAttribute("id_morador");

            List lista = visitantedao.listarVisitantes(Integer.parseInt(idMoardor),"");
            for (Object visitante : lista) {
                var vt = (Visitante) visitante;
                String nome = vt.getNome() +" "+ vt.getSobrenome();
                tabela.addCell(nome);
                tabela.addCell(vt.getDocumento());
                tabela.addCell(vt.getFone());
            }
            documento.add(tabela);
        } catch (Exception e) {
            System.out.println(e);
            documento.close();
        }
    }
}