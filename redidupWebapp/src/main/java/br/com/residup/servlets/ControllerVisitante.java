package br.com.residup.servlets;

import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.residup.models.Morador;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import br.com.residup.models.Visitante;
import br.com.residup.daos.VisitanteDao;


@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
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
        if (action.equals("/main")) {
            visitantes(request, response);
            return;
        }
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
        if (action.equals("/delete")) {
            removerContato(request, response);
        }
        if (action.equals("/report")) {
            gerarRelatorio(request, response);
            return;
        }
        response.sendRedirect("index.html");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
    }

    protected void visitantes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Visitante> lista = VisitanteDao.listarVisitantes();
        request.setAttribute("visitantes", lista);
        RequestDispatcher rd = request.getRequestDispatcher("visitantes.jsp");
        rd.forward(request, response);
    }


    protected void adicionarVisitante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] nomeSobrenome = Visitante.separarNomeSobrenome(request.getParameter("nome"));
        String nome = nomeSobrenome[0];
        String sobrenome = nomeSobrenome[1];
        String domuento = request.getParameter("email");
        String fone = request.getParameter("fone");
        var visitante = new Visitante(nome,sobrenome,domuento) ;
        visitante.setFone(fone);
        VisitanteDao.inserirVisitante(visitante);
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
    }


    protected void editarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        visitante.setId(request.getParameter("idcon"));
        visitante.setNome(request.getParameter("nome"));
        visitante.setFone(request.getParameter("fone"));
        visitante.setDocumento(request.getParameter("email"));
        VisitanteDao.alterarVisitante(visitante);
        response.sendRedirect("main");
    }


    protected void removerContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        visitante.setId(request.getParameter("idcon"));
        VisitanteDao.deletarVisitante(visitante);
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
            PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
            PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
            tabela.addCell(col1);
            tabela.addCell(col2);
            tabela.addCell(col3);
            ArrayList<Visitante> lista = VisitanteDao.listarVisitantes();
            for (int i = 0; i < lista.size(); i++) {
                tabela.addCell(lista.get(i).getNome());
                tabela.addCell(lista.get(i).getFone());
                tabela.addCell(lista.get(i).getDocumento());
            }
            documento.add(tabela);
            documento.close();
        } catch (Exception e) {
            System.out.println(e);
            documento.close();
        }
    }
}