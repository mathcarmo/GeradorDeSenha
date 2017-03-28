package geradordesenha.matheus.com.br.geradordesenha.Entity;

/**
 * Created by Desenvolvimento on 09/02/2017.
 */
public class Login implements EntityPersist {

    private int id;
    private String login;
    private String senha;
    private String email;
    private String keyRecover;


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKeyRecover() {
        return keyRecover;
    }

    public void setKeyRecover(String keyRecover) {
        this.keyRecover = keyRecover;
    }
}
