package geradordesenha.matheus.com.br.geradordesenha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import geradordesenha.matheus.com.br.geradordesenha.DAO.LoginDAO;
import geradordesenha.matheus.com.br.geradordesenha.Email.SendEmail;
import geradordesenha.matheus.com.br.geradordesenha.Entity.Login;
import geradordesenha.matheus.com.br.geradordesenha.Util.GeradorSenha;
import geradordesenha.matheus.com.br.geradordesenha.Util.RecursosUteis;

public class RecuperarSenhaActivity extends AppCompatActivity {

    private EditText edtKeyRecover;
    private Button btnGeraKey, btnValidar;
    private LoginDAO lDao;
    private List<Login> listLogin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);
        lDao = new LoginDAO(this);
        listLogin =  lDao.recuperarTodos();

        btnGeraKey = (Button) findViewById(R.id.btnRecover);
        btnValidar = (Button) findViewById(R.id.btnValidar);
        edtKeyRecover = (EditText) findViewById(R.id.edtKeyRecover);

        btnGeraKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarKeyRecover();
            }
        });
    }



    private void sendEmail() {
        //Getting content for email
        GeradorSenha g = new GeradorSenha();

        String email = listLogin.get(0).getEmail().toString().trim();
        String keyRecover = g.gerarSenhaAleatoria(5,true,false,true,false).toString().trim();
        String titulo = "RECUPERAR SENHA - GERADOR DE SENHA".trim();
        listLogin.get(0).setKeyRecover(keyRecover);
        lDao.salvar(listLogin.get(0));

        //Creating SendMail object
        SendEmail sm = new SendEmail(this, email, titulo, keyRecover);

        //Executing sendmail to send email
        sm.execute();
        edtKeyRecover.setEnabled(true);
        btnValidar.setEnabled(true);
    }

    private void validarKeyRecover(){
        String tmpKeyRecover = edtKeyRecover.getText().toString();
        String keyRecover = listLogin.get(0).getKeyRecover().toString();

        if (tmpKeyRecover.equals(keyRecover)){
            Intent intent = new Intent();
            intent.setClass(RecuperarSenhaActivity.this, AlterarSenhaActivity.class);
            startActivity(intent);

        }else{
            RecursosUteis.dialogOk(this,"Recuperar Senha","Codigo de Recuperação Inválido","OK",R.drawable.icone);
        }
    }
}
