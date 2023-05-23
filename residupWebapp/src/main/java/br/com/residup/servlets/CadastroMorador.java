package br.com.residup.servlets;

import br.com.residup.models.IconAlertJS;
import br.com.residup.daos.MoradorDao;
import br.com.residup.models.Morador;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static br.com.residup.shared.Uteis.scriptMensagemAlertJs;
import static org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent;


@WebServlet(urlPatterns = {"/cadastro_morador", "/create_morador", "/selectMorador", "/updateMorador", "/deleteMorador", "/updatePerfilMorador"})
public class CadastroMorador extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CadastroMorador.class.getName());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/cadastro_morador")){
            Boolean check = (Boolean) request.getSession().getAttribute("check");
            String mgs = (String) request.getSession().getAttribute("mgsJS");
            if (check != null) {
                if (check) {
                    String msg = mgs;
                    request.setAttribute("mensagem", msg);
                }
            }

            request.getSession().removeAttribute("check");
            request.getSession().removeAttribute("mgsJS");
            request.getRequestDispatcher("Telas/registroMorador.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("Telas/registroMorador.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/create_morador")) {
            try {

                Map<String, String> parameters = uploadImage(request);
                String fotoMoradorImagePath = parameters.get("image");
                var nome = parameters.get("nomeMorador");
                var sobrenome = parameters.get("sobrenomeMorador");
                var cpf = parameters.get("cpfMorador");
                var rg = parameters.get("rgMorador");
                var bloco = parameters.get("blocoMorador");
                var email = parameters.get("emailMorador");
                var telefone = parameters.get("telefoneMorador");
                var numero_apartamento = parameters.get("numero_apartamentoMorador");
                var senha = parameters.get("senhaMorador");




                System.out.println(fotoMoradorImagePath);
                var morador = new Morador(nome,sobrenome,cpf,rg,numero_apartamento,bloco,senha);
                morador.setEnderecoFoto(fotoMoradorImagePath);



                if(MoradorDao.checkCadastro(cpf)){
                    String msgJs = scriptMensagemAlertJs(IconAlertJS.warning, "Atenção", "CPF já cadastrado!");
                    request.getSession().setAttribute("mgsJS", msgJs);
                    request.getSession().setAttribute("check", true);
                    response.sendRedirect("/cadastro_morador");
                    return;
                }
                if  (MoradorDao.createMorador(morador)){
                    String msgJs = scriptMensagemAlertJs(IconAlertJS.success, "Sucesso", "Cadastro realizado com sucesso!");
                    request.getSession().setAttribute("mgsJS", msgJs);
                    request.getSession().setAttribute("check", true);
                    response.sendRedirect("/cadastro_morador");
                    return;
                }
                String msgJs = scriptMensagemAlertJs(IconAlertJS.error, "Erro", "Ocorreu um erro no cadastro. Tente novamente, mais tarde!");
                request.getSession().setAttribute("mgsJS", msgJs);
                request.getSession().setAttribute("check", true);
                response.sendRedirect("/cadastro_morador");
                return;

            } catch (Exception e){
                System.out.println(e);
            }
        }
        if(action.equals("/updatePerfilMorador")){
            updatePerfilMorador(request, response);
            return;
        }


    }


    public void updatePerfilMorador(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getServletPath();
        Map<String, String> parameters = uploadImage(request);
        String fotoMoradorImagePath = parameters.get("image");
        var senha = parameters.get("Senha");
        var confirmarSenha = parameters.get("Confs");


            var morador = new Morador(null,null,"52201779821",null,null,null,senha);
            morador.setEnderecoFoto(fotoMoradorImagePath);

            if(!MoradorDao.updateMoradorPerfil(morador)){
                String msgJs = scriptMensagemAlertJs(IconAlertJS.error, "Erro", "Ocorreu um erro na alteração do Perfil. Tente novamente, mais tarde!");
                request.getSession().setAttribute("mgsJS", msgJs);
                request.getSession().setAttribute("check", true);
                response.sendRedirect("/reservas");
            }
            response.sendRedirect("/perfilMorador");





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
