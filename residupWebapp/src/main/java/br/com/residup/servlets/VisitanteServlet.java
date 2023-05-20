package br.com.residup.servlets;

import java.io.Serial;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.residup.models.Visitante;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import br.com.residup.daos.VisitanteDao;


@WebServlet(urlPatterns = {"/visitantes", "/insert", "/select", "/update", "/delete", "/report"})
public class VisitanteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/visitantes")) {
            visitantes(request, response);
            return;
        }
        if (action.equals("/newVisitante")) {
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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

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
    }



    protected void visitantes(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        ArrayList<br.com.residup.models.Visitante> lista = VisitanteDao.listarVisitantes();
        request.setAttribute("listaVisitantes", lista);
        System.out.println(request.getParameter("validator"));
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
        if (VisitanteDao.inserirVisitante(visitante)){
            request.setAttribute("validator", true);
        }else
            request.getSession().setAttribute("validator", false);
        response.sendRedirect("visitantes");

    }



    protected void buscarVisitante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        br.com.residup.models.Visitante visitante = new br.com.residup.models.Visitante();
        visitante.setId(request.getParameter("id"));
        VisitanteDao.selecionarVisitante(visitante);
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
        visitante.setId(request.getParameter("id"));
        visitante.setNome(request.getParameter("nome"));
        visitante.setSobrenome(request.getParameter("sobrenome"));
        visitante.setDocumento(request.getParameter("documento"));
        visitante.setFone(request.getParameter("fone"));
        if (VisitanteDao.alterarVisitante(visitante)){
            request.getSession().setAttribute("validador", true);
        }else
            request.getSession().setAttribute("validador", false);
        response.sendRedirect("visitantes");
    }

    protected void removerVisitante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        br.com.residup.models.Visitante visitante = new br.com.residup.models.Visitante();
        visitante.setId(request.getParameter("id"));
        if (VisitanteDao.deletarVisitante(visitante)){
            request.getSession().setAttribute("validador", true);
        }else
            request.getSession().setAttribute("validador", false);
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
            ArrayList<br.com.residup.models.Visitante> lista = VisitanteDao.listarVisitantes();
            for (int i = 0; i < lista.size(); i++) {
                String nome = lista.get(i).getNome() +" "+ lista.get(i).getSobrenome();
                tabela.addCell(nome);
                tabela.addCell(lista.get(i).getDocumento());
                tabela.addCell(lista.get(i).getFone());
            }
            documento.add(tabela);
        } catch (Exception e) {
            System.out.println(e);
            documento.close();
        }
    }
}