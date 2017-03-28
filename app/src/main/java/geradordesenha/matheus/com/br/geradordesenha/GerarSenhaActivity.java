package geradordesenha.matheus.com.br.geradordesenha;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import geradordesenha.matheus.com.br.geradordesenha.DAO.SenhaDAO;
import geradordesenha.matheus.com.br.geradordesenha.Entity.SenhaGerada;
import geradordesenha.matheus.com.br.geradordesenha.Util.RecursosUteis;
import geradordesenha.matheus.com.br.geradordesenha.Util.GeradorSenha;

public class GerarSenhaActivity extends Activity {
    CheckBox minuscula;
    CheckBox maiuscula;
    CheckBox numero;
    CheckBox caracter;

    SeekBar seekBarTamanho;
    EditText editTextTamanho;
    EditText editTextTitulo;
    TextView textViewSenhaGerada;

    Button gerarSenha;
    Button copiarSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_senha);

        minuscula = (CheckBox) findViewById(R.id.checkBoxMinuscula);
        maiuscula = (CheckBox) findViewById(R.id.checkBoxMaiuscula);
        numero = (CheckBox) findViewById(R.id.checkBoxNumero);
        caracter = (CheckBox) findViewById(R.id.checkBoxCaracter);

        editTextTamanho = (EditText) findViewById(R.id.editTextTamanho);
        seekBarTamanho = (SeekBar) findViewById(R.id.seekBarTamanho);
        seekBarTamanho.setProgress(1);
        seekBarTamanho.setMax(20);
        seekBarTamanho.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editTextTamanho.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        editTextTitulo = (EditText) findViewById(R.id.editTextTitulo);
        textViewSenhaGerada = (TextView) findViewById(R.id.textViewSenhaGerada);

        gerarSenha = (Button) findViewById(R.id.buttonGerarSenha);
        copiarSenha = (Button) findViewById(R.id.buttonCopiarSenha);

    }

    public void btnGerarSenha(View v){
        if (minuscula.isChecked()|| maiuscula.isChecked()|| numero.isChecked()|| caracter.isChecked()){
            if (!editTextTamanho.getText().toString().equals("")){
                GeradorSenha geradorSenha = new GeradorSenha();
                textViewSenhaGerada.setText(geradorSenha.gerarSenhaAleatoria(Integer.parseInt(editTextTamanho.getText().toString()),
                        minuscula.isChecked(), maiuscula.isChecked(), numero.isChecked(), caracter.isChecked()));
            }else{
                RecursosUteis.dialogOk(this,"Gerar Senha","Informe o tamanho da senha!","OK",R.drawable.icone);
            }

        }else{
            RecursosUteis.dialogOk(this,"Gerar Senha","É necessário marcar pelo menos um checkbox!","OK",R.drawable.icone);
        }
    }

    public void btnCopiar(View v){
      if (!textViewSenhaGerada.getText().equals("")){
          RecursosUteis.ClipBoard(this, getBaseContext(), textViewSenhaGerada.getText().toString(),
                  "Senha Copiada com sucesso!");
      }else{
          RecursosUteis.ClipBoard(this, getBaseContext(), textViewSenhaGerada.getText().toString(),
                  "Para utilizar este recurso primeiro gere uma senha!");
      }

    }


    public void salvarSenha(View v){
        SenhaDAO senhaDAO = new SenhaDAO(this);
        SenhaGerada senha = new SenhaGerada();

        senha.setTitulo(editTextTitulo.getText().toString());
        senha.setSenhaGerada(textViewSenhaGerada.getText().toString());
        senhaDAO.salvar(senha);
        List<SenhaGerada> list = senhaDAO.recuperarTodos();
        Toast.makeText(getApplicationContext(), getString(R.string.msg_salve_senha), Toast.LENGTH_SHORT).show();
    }
}
