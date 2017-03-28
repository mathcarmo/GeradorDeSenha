package geradordesenha.matheus.com.br.geradordesenha.Entity;

/**
 * Created by Desenvolvimento on 09/02/2017.
 */
public class SenhaGerada implements EntityPersist {
    private int id;
    private String titulo;
    private String senhaGerada;


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSenhaGerada() {
        return senhaGerada;
    }

    public void setSenhaGerada(String senhaGerada) {
        this.senhaGerada = senhaGerada;
    }

    @Override
    public String toString() {
        return "Titulo:"+ this.titulo+"\n Senha:"+this.senhaGerada;
    }
}
