package geradordesenha.matheus.com.br.geradordesenha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by mathe on 12/03/2017.
 */

public class MenuActivity extends Activity {

    private Button alterarDados;
    private Button listarSenhas;
    private Button gerarSenha;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        alterarDados = (Button) findViewById(R.id.buttonAlterarDados);
        listarSenhas = (Button) findViewById(R.id.buttonListaS);
        gerarSenha = (Button) findViewById(R.id.buttonGerarSenha);


        alterarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MenuActivity.this, AlterarSenhaActivity.class);
                startActivity(intent);
            }
        });

        listarSenhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MenuActivity.this, ListSenhaActivity.class);
                startActivity(intent);
            }
        });

        gerarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MenuActivity.this, GerarSenhaActivity.class);
                startActivity(intent);
            }
        });

    }

    }


