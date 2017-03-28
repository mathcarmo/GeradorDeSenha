package geradordesenha.matheus.com.br.geradordesenha;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import geradordesenha.matheus.com.br.geradordesenha.DAO.SenhaDAO;
import geradordesenha.matheus.com.br.geradordesenha.Entity.SenhaGerada;
import geradordesenha.matheus.com.br.geradordesenha.R;

public class ListSenhaActivity extends AppCompatActivity {

    private List<SenhaGerada> senhas;
    private ListView lView;
    private EditText eText;
    private List<SenhaGerada> pesquisa = new ArrayList<SenhaGerada>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_senha);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Declarando os objetos do main.xml
        lView = (ListView) findViewById(R.id.lView);
        eText = (EditText) findViewById(R.id.eText);

        senhas = new SenhaDAO(getBaseContext()).recuperarTodos();

        lView.setAdapter(new ArrayAdapter<SenhaGerada>(this, android.R.layout.simple_list_item_1, senhas));

        // Função responsável pela pesquisa
        Pesquisar();

        eText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }

            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                Pesquisar();

                lView.setAdapter(new ArrayAdapter<SenhaGerada>(ListSenhaActivity.this, android.R.layout.simple_list_item_1, pesquisa));
            }
        });
    }

    public void Pesquisar() {
        int textlength = eText.getText().length();
        pesquisa.clear();

        for (int i = 0; i < senhas.size(); i++ ) {
            if (textlength <= senhas.get(i).getTitulo().length()) {
                if (eText.getText().toString().equalsIgnoreCase((String)senhas.get(i).getTitulo().subSequence(0, textlength))) {
                    pesquisa.add(senhas.get(i));
                }
            }
        }
    }


}
