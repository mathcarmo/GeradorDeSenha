package geradordesenha.matheus.com.br.geradordesenha.DAO;

import android.content.ContentValues;
import android.content.Context;

import geradordesenha.matheus.com.br.geradordesenha.Entity.Login;


/**
 * Created by Desenvolvimento on 09/02/2017.
 */
public class LoginDAO extends BaseDAO<Login>{

         public static final String NOME_TABELA = "LOGIN";
        public static final String COLUNA_ID = "id";
        public static final String COLUNA_LOGIN = "login";
        public static final String COLUNA_SENHA = "senha";
        public static final String COLUNA_EMAIL = "email";
        public static final String COLUNA_KEYRECOVER = "keyRecover";

        public static final String SCRIPT_CRIACAO_TABELA_LOGIN = "CREATE TABLE " + NOME_TABELA + " ("
                + COLUNA_ID + " INTEGER PRIMARY KEY autoincrement, " + COLUNA_LOGIN + " TEXT, "
                + COLUNA_SENHA + " TEXT," + COLUNA_EMAIL + " TEXT, "+ COLUNA_KEYRECOVER +" TEXT)";

        public static final String SCRIPT_DELECAO_TABELA = "DROP TABLE IF EXISTS " + NOME_TABELA;
        public static LoginDAO instance;

        public static LoginDAO getInstance(Context context) {
            if (instance == null)
                instance = new LoginDAO(context);
            return instance;
        }

        public LoginDAO(Context context) {
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
        public ContentValues entidadeParacontentValues(Login login) {

            ContentValues values = new ContentValues();
            if (login.getId() > 0) {
                values.put(COLUNA_ID, login.getId());
            }
            values.put(COLUNA_LOGIN, login.getLogin());
            values.put(COLUNA_SENHA, login.getSenha());
            values.put(COLUNA_EMAIL, login.getEmail());
            values.put(COLUNA_KEYRECOVER, login.getKeyRecover());
            return values;
        }

        @Override
        public Login contentValuesParaEntidade(ContentValues contentValues) {
            Login usuario = new Login();
            usuario.setId(contentValues.getAsInteger(COLUNA_ID));
            usuario.setLogin(contentValues.getAsString(COLUNA_LOGIN));
            usuario.setSenha(contentValues.getAsString(COLUNA_SENHA));
            usuario.setEmail(contentValues.getAsString(COLUNA_EMAIL));
            usuario.setKeyRecover(contentValues.getAsString(COLUNA_KEYRECOVER));
            return usuario;
        }
}
