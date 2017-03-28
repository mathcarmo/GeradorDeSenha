package geradordesenha.matheus.com.br.geradordesenha.DAO;

import android.content.ContentValues;
import android.content.Context;


import geradordesenha.matheus.com.br.geradordesenha.Entity.SenhaGerada;

/**
 * Created by Desenvolvimento on 09/02/2017.
 */
public class SenhaDAO extends BaseDAO<SenhaGerada>{

    public static final String NOME_TABELA = "SENHA_GERADA";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_TITULO = "titulo";
    public static final String COLUNA_SENHA_GERADA = "senhaGerada";

    public static final String SCRIPT_CRIACAO_TABELA_SENHA = "CREATE TABLE " + NOME_TABELA + " ("
            + COLUNA_ID + " INTEGER PRIMARY KEY autoincrement, " + COLUNA_TITULO + " TEXT, "
            + COLUNA_SENHA_GERADA + " TEXT)";

    public static final String SCRIPT_DELECAO_TABELA = "DROP TABLE IF EXISTS " + NOME_TABELA;
    public static SenhaDAO instance;

    public static SenhaDAO getInstance(Context context) {
        if (instance == null)
            instance = new SenhaDAO(context);
        return instance;
    }

    public SenhaDAO(Context context) {
        super(context);
    }

    @Override
    public String getNomeColunaPrimaryKey() {
        return COLUNA_ID;
    }

    @Override
    public String getNomeTabela() {
        return NOME_TABELA;
    }

    @Override
    public ContentValues entidadeParacontentValues(SenhaGerada senha) {

        ContentValues values = new ContentValues();
        if (senha.getId() > 0) {
            values.put(COLUNA_ID, senha.getId());
        }
        values.put(COLUNA_TITULO, senha.getTitulo());
        values.put(COLUNA_SENHA_GERADA, senha.getSenhaGerada());
        return values;
    }

    @Override
    public SenhaGerada contentValuesParaEntidade(ContentValues contentValues) {
        SenhaGerada senha = new SenhaGerada();
        senha.setId(contentValues.getAsInteger(COLUNA_ID));
        senha.setTitulo(contentValues.getAsString(COLUNA_TITULO));
        senha.setSenhaGerada(contentValues.getAsString(COLUNA_SENHA_GERADA));
        return senha;
    }
}
