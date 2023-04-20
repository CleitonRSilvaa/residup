package br.com.residup.servlets;

import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import br.com.residup.models.Visitante;
import br.com.residup.daos.VisitanteDao;


@WebServlet(urlPatterns = { "/Controller", "/Visitante", "/insert", "/select", "/update", "/delete", "/report"})
public class ControllerVisitante extends HttpServlet {

    private static final long serialVersionUID = 1L;

    Visitante visitante = new Visitante();
    public ControllerVisitante() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/Visitante")) {
//            request.getSession().setAttribute("validador", false);
            visitantes(request, response);
            return;
        }
        if (action.equals("/delete")) {
            removerContato(request, response);
            return;
        }
        if (action.equals("/report")) {
            gerarRelatorio(request, response);
            return;
        }
        if (action.equals("/visitor")) {
            request.getSession().setAttribute("validator", true);
            visitantes(request, response);
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
            listarContato(request, response);
            return;
        }
        if (action.equals("/update")) {
            editarContato(request, response);
            return;
        }
        if (action.equals("/visitor")) {
            request.getSession().setAttribute("validator", true);
            visitantes(request, response);
            return;
        }


    }

    protected void visitantes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Visitante> lista = VisitanteDao.listarVisitantes();
        request.setAttribute("visitantes", lista);

        request.getParameter("validator");
        RequestDispatcher rd = request.getRequestDispatcher("visitantes.jsp");
        rd.forward(request, response);
    }


    protected void adicionarVisitante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String documento = request.getParameter("documento");
        String fone = request.getParameter("fone");
        var visitante = new Visitante(nome,sobrenome,documento) ;
        visitante.setFone(fone);
        if (VisitanteDao.inserirVisitante(visitante)){
            request.getSession().setAttribute("validator", true);
        }else
            request.getSession().setAttribute("validator", false);
        response.sendRedirect("main");

    }



    protected void listarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        visitante.setId(request.getParameter("id"));
        VisitanteDao.selecionarVisitante(visitante);
        request.setAttribute("id", visitante.getId());
        request.setAttribute("nome", visitante.getNome());
        request.setAttribute("fone", visitante.getFone());
        request.setAttribute("email", visitante.getDocumento());
        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
        rd.forward(request, response);
        response.sendRedirect("main");
    }


    protected void editarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        visitante.setId(request.getParameter("id"));
        visitante.setNome(request.getParameter("nome"));
        visitante.setSobrenome(request.getParameter("sobrenome"));
        visitante.setDocumento(request.getParameter("documento"));
        visitante.setFone(request.getParameter("fone"));
        if (VisitanteDao.alterarVisitante(visitante)){
            request.getSession().setAttribute("validador", true);
        }else
            request.getSession().setAttribute("validador", false);
        response.sendRedirect("main");
    }

    protected void removerContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        visitante.setId(request.getParameter("id"));
        if (VisitanteDao.deletarVisitante(visitante)){
            request.getSession().setAttribute("validador", true);
        }else
            request.getSession().setAttribute("validador", false);
        response.sendRedirect("main");
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
            ArrayList<Visitante> lista = VisitanteDao.listarVisitantes();
            for (int i = 0; i < lista.size(); i++) {
                String nome = lista.get(i).getNome() +" "+ lista.get(i).getSobrenome();
                tabela.addCell(nome);
                tabela.addCell(lista.get(i).getDocumento());
                tabela.addCell(lista.get(i).getFone());
            }
            documento.add(tabela);
            documento.close();
        } catch (Exception e) {
            System.out.println(e);
            documento.close();
        }
    }
}