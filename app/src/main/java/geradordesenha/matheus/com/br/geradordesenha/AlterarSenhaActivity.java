package geradordesenha.matheus.com.br.geradordesenha;

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
import android.widget.Toast;

import java.util.List;

import geradordesenha.matheus.com.br.geradordesenha.DAO.LoginDAO;
import geradordesenha.matheus.com.br.geradordesenha.Entity.Login;
import geradordesenha.matheus.com.br.geradordesenha.Util.RecursosUteis;

public class AlterarSenhaActivity extends AppCompatActivity {

    private EditText usuario, senha, cSenha;
    private TextInputLayout inputLayoutName, inputLayoutCSenha, inputLayoutPassword;
    private Button salvar;
    private Login login;
    private LoginDAO lDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);

        login = new Login();
        lDao = new LoginDAO(this);
        login = lDao.recuperarTodos().get(0);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutCSenha = (TextInputLayout) findViewById(R.id.input_layout_csenha);

        usuario = (EditText) findViewById(R.id.editTextUsuario);
        senha = (EditText) findViewById(R.id.editTextSenha);
        cSenha = (EditText) findViewById(R.id.editTextCSenha);
        salvar = (Button) findViewById(R.id.buttonSalvarLogin);

        senha.addTextChangedListener(new AlterarSenhaActivity.MyTextWatcher(senha));
        cSenha.addTextChangedListener(new AlterarSenhaActivity.MyTextWatcher(cSenha));

        usuario.setText(login.getLogin().toString());
        usuario.setEnabled(false);

    }



    public void btnSalvarLogin(View v){
        if (!senha.getText().toString().isEmpty()){
            RecursosUteis.dialogOk(this,"Atenção","As senhas estão vazias \n Tente Novamente","OK",R.drawable.icone);
        }else{
                (!senha.getText().toString().equals(cSenha.getText().toString())) {
                RecursosUteis.dialogOk(this, "Alterar Login", "As senhas não são iguais \n Tente novamente", "OK", R.drawable.icone);
            }
        }else if{
            login.setLogin(usuario.getText().toString());
            login.setSenha(senha.getText().toString());
            lDao.editar(login);
            List<Login> teste =  lDao.recuperarTodos();
            RecursosUteis.dialogOk(this,"Alterar Login","Login alterado com sucesso!","OK",R.drawable.icone);
            Intent intent = new Intent();
            intent.setClass(AlterarSenhaActivity.this, LoginActivity.class);
            startActivity(intent);

        }

    }

    private void submitForm() {

        if (!validatePassword()) {
            return;
        }

        if (!validateCPassword()) {
            return;
        }

        Toast.makeText(getApplicationContext(), getString(R.string.msg_salve_login), Toast.LENGTH_SHORT).show();
    }

    private boolean validatePassword() {
        if (senha.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(senha);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateCPassword() {
        if (cSenha.getText().toString().trim().isEmpty()) {
            inputLayoutCSenha.setError(getString(R.string.err_msg_password));
            requestFocus(cSenha);
            return false;
        } else {
            inputLayoutCSenha.setErrorEnabled(false);
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

                case R.id.editTextSenha:
                    validatePassword();
                    break;

                case R.id.editTextCSenha:
                    validateCPassword();
                    break;
            }
        }
    }

}
