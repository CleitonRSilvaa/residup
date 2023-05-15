package br.com.residup.servlets;


import br.com.residup.daos.MoradorDao;
import br.com.residup.models.Morador;
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
import java.util.*;

import static org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent;

@WebServlet("/encontrar-perfil-morador")
public class PerfilMorador extends HttpServlet{
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            try {
                Morador morador = new MoradorDao().buscarMorador("52201779821");

                req.setAttribute("morador", morador);

                req.getRequestDispatcher("perfil.jsp").forward(req, resp);

            } catch (Exception exception){


            }

        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> parameters = uploadImage(req);

        String fotoMoradorImagePath = parameters.get("image");
        System.out.println(fotoMoradorImagePath);
        doGet(req, resp);


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
}
