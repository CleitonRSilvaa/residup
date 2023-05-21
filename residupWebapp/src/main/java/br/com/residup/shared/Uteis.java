package br.com.residup.shared;

import br.com.residup.models.IconAlertJS;

public class Uteis {

    public static String scriptMensagemAlertJs(IconAlertJS iconAlertJS, String titulo, String messagem) {
        String mgs = "Swal.fire(\n '" + titulo + "',\n'" + messagem + "'\n,'" + iconAlertJS + "'\n" + ");\n";
        return mgs;
    }

}
