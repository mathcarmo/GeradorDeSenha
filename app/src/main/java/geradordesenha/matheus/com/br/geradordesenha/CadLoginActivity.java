package geradordesenha.matheus.com.br.geradordesenha;

import android.app.Activity;
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

public class CadLoginActivity extends Activity {

    private EditText usuario, senha, email;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword;
    Button salvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_login);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

        usuario = (EditText) findViewById(R.id.editTextUsuario);
        senha = (EditText) findViewById(R.id.editTextSenha);
        email = (EditText) findViewById(R.id.editTextEmail);
        salvar = (Button) findViewById(R.id.buttonSalvarLogin);

        usuario.addTextChangedListener(new MyTextWatcher(usuario));
        email.addTextChangedListener(new MyTextWatcher(email));
        senha.addTextChangedListener(new MyTextWatcher(senha));



    }

    public void btnSalvarLogin(View v){
        Login login = new Login();
        login.setLogin(usuario.getText().toString());
        login.setSenha(senha.getText().toString());
        login.setEmail(email.getText().toString());
        new LoginDAO(getApplicationContext()).salvar(login);
    //    RecursosUteis.dialogOk(this,"Salvar Login","Login Salvo com sucesso!","OK",R.drawable.icone);
        submitForm();
    }


    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        Toast.makeText(getApplicationContext(), getString(R.string.msg_salve_login), Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (usuario.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(usuario);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = this.email.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(this.email);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
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

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
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
                case R.id.editTextUsuario:
                    validateName();
                    break;
                case R.id.editTextEmail:
                    validateEmail();
                    break;
                case R.id.editTextSenha:
                    validatePassword();
                    break;
            }
        }
    }
}
