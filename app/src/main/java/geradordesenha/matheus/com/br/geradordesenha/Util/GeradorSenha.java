package geradordesenha.matheus.com.br.geradordesenha.Util;

/**
 * Created by Desenvolvimento on 09/02/2017.
 */
public class GeradorSenha {


    public String gerarSenhaAleatoria(int tamanho, boolean maiuscula, boolean minuscula,
                                          boolean numero, boolean caracterEspecial) {
        int tamanhoSenha = 0;
        String[] caracteres = {};


        String[] maiusculas = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z"};

        String[] minusculas ={"a", "b","a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z"};

        String[] numeros = { "0","1","2","3","4","5","6","7","8","9" };

        String[] caracter = {"!","@","#","$","%","&","*","(",")","+","=","-","{","}","[","]","/" };

        if (maiuscula){
            caracteres = maiusculas;
        }
        if (minuscula){
            caracteres = concatenar(caracteres,minusculas);
        }
        if (numero){
            caracteres=concatenar(caracteres,numeros);
        }
        if(caracterEspecial){
            caracteres=concatenar(caracteres,caracter);
        }

        StringBuilder senha = new StringBuilder();
        tamanhoSenha = tamanho;

        for (int i = 0; i < tamanhoSenha; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            senha.append(caracteres[posicao]);
        }
        return senha.toString();
    }

    private String [] concatenar(String [] array1, String [] array2){

        String[] array3 = new String [array1.length + array2.length];
        int x=0;
        for (int i = 0; i < array3.length; i++) {
            if (i < array1.length) {
                array3[i] = array1[i];
            } else {
                array3[i] = array2[x];
                x++;
            }
        }
        return array3;
    }
}
