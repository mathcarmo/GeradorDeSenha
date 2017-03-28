package geradordesenha.matheus.com.br.geradordesenha.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by Desenvolvimento on 09/02/2017.
 */
public class RecursosUteis {

    public static void ClipBoard(Activity activity, Context contexto, String conteudo, String mensagem) {
        ClipboardManager cm = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(conteudo);
        Toast toast = Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void dialogOk(Context context, String titulo, String mensagem, String textoBotao, int icone) {
        AlertDialog alerta;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        builder.setPositiveButton(textoBotao, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });

        builder.setIcon(icone);
        alerta = builder.create();
        alerta.show();
    }


}
