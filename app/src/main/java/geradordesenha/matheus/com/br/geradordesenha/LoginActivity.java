package geradordesenha.matheus.com.br.geradordesenha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.AccessController;
import java.util.List;

import geradordesenha.matheus.com.br.geradordesenha.DAO.LoginDAO;
import geradordesenha.matheus.com.br.geradordesenha.Entity.Login;
import geradordesenha.matheus.com.br.geradordesenha.Util.RecursosUteis;

public class LoginActivity extends Activity {
    private EditText user, password;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword;
    private Button logar;
    private TextView cadLogin, rSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

        user = (EditText) findViewById(R.id.input_user);
        password = (EditText) findViewById(R.id.input_password);
        logar = (Button) findViewById(R.id.btn_signup);
        cadLogin = (TextView) findViewById(R.id.textViewCadLogin);
        rSenha = (TextView) findViewById(R.id.textViewRSenha);

        user.addTextChangedListener(new MyTextWatcher(user));
        password.addTextChangedListener(new MyTextWatcher(password));

        cadLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDAO loginDAO = new LoginDAO(getBaseContext());
                List<Login> loginList = loginDAO.recuperarTodos();
                if (loginList.isEmpty()) {
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, CadLoginActivity.class);
                    startActivity(intent);
                }else{
                    RecursosUteis.dialogOk(LoginActivity.this,"Atenção","Já existe login usuário cadastrado!","OK",R.drawable.icone);
                }
            }
        });

        rSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDAO loginDAO = new LoginDAO(getBaseContext());
                List<Login> listLogin =  loginDAO.recuperarTodos();
                if (!listLogin.isEmpty()){
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, RecuperarSenhaActivity.class);
                    startActivity(intent);
                }else{
                    RecursosUteis.dialogOk(LoginActivity.this, "Atenção", "Não tem usuário cadastrado!", "OK", R.drawable.icone);
                }

            }
        });

    }

    public void linkCadLogin(View v){

    }

    public void btnLogar(View v){
        LoginDAO loginDAO = new LoginDAO(getBaseContext());
        List<Login> listLogin =  loginDAO.recuperarTodos();

        if (!user.getText().toString().equals("")){
            if (!password.getText().toString().equals("")){


                if (!listLogin.isEmpty()){
                    if (user.getText().toString().equals(listLogin.get(0).getLogin().toString())){
                        if(password.getText().toString().equals(listLogin.get(0).getSenha().toString())){
                            Intent intent = new Intent();
                            intent.setClass(this, MenuActivity.class);
                            startActivity(intent);
                        }else{
                            RecursosUteis.dialogOk(this, "Login", "Senha Invalida!", "OK", R.drawable.icone);
                        }
                    }else{
                        RecursosUteis.dialogOk(this,"Login","Login Invalido!","OK",R.drawable.icone);
                    }
                }else{
                    RecursosUteis.dialogOk(this,"Login","Nenhum usuario cadastrado!","OK",R.drawable.icone);
                }
            }else{
                RecursosUteis.dialogOk(this,"Login","Informe a senha!","OK",R.drawable.icone);
            }
        }else{
            RecursosUteis.dialogOk(this,"Login","Informe o usuario!","OK",R.drawable.icone);
        }
    }

    private boolean validateName() {
        if (user.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(user);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (password.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(password);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_user:
                    validateName();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }

}
